package org.dayup.avatar.web.api;

import org.dayup.avatar.model.define.ResponseInfo;
import org.dayup.avatar.model.vo.DocumentQuery;
import org.dayup.avatar.model.vo.DocumentVo;
import org.dayup.avatar.service.docs.IDocumentService;
import org.dayup.avatar.support.BaseApi;
import org.dayup.avatar.support.common.IDSecure;
import org.dayup.avatar.support.constants.EMessage;
import org.dayup.avatar.support.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/vue/docs/doc")
public class DocumentApi extends BaseApi {


    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentApi.class);

    @Autowired
    private IDocumentService documentService;

    @GetMapping("/browse")
    public ResponseInfo browse(@RequestParam("id") String encodeId) {

        try {
            Long id = IDSecure.decode(encodeId);
            DocumentVo documentVo = documentService.getDocument(id);
            return wrapSuccess(documentVo);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return wrapError();
        }
    }

    @PostMapping("/save")
    public ResponseInfo create(@RequestBody DocumentVo documentVo) {
        try {
            documentVo = documentService.save(documentVo);
            return wrapResponse(EMessage.SAVE_SUCCESS, documentVo);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return wrapError();
        }
    }

    @PostMapping("/search")
    public ResponseInfo search(@RequestBody DocumentQuery query) {
        return wrapSuccess(documentService.search(query));
    }


    @PostMapping("/drop")
    public ResponseInfo drop(@RequestBody DocumentVo documentVo) {
        try {
            documentService.drop(extractIds(Arrays.asList(documentVo)));
            return wrapResponse(EMessage.DROP_SUCCESS);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return wrapError();
        }
    }


    @PostMapping("/move")
    public ResponseInfo move(@RequestBody List<DocumentVo> documentVos) {

        try {
            documentService.move(documentVos);
            return wrapResponse(EMessage.MOVE_SUCCESS);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return wrapError();
        }
    }

    private List<Long> extractIds(List<DocumentVo> documentVoList) {
        List<Long> docIds = new ArrayList<>();
        for (DocumentVo vo : documentVoList) {
            docIds.add(IDSecure.decode(vo.getId()));
        }
        return docIds;
    }

}
