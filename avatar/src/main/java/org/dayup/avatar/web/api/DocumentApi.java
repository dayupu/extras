package org.dayup.avatar.web.api;

import org.dayup.avatar.model.define.ResponseInfo;
import org.dayup.avatar.model.vo.DocumentVo;
import org.dayup.avatar.service.docs.IDocumentService;
import org.dayup.avatar.support.BaseApi;
import org.dayup.avatar.support.common.IDSecure;
import org.dayup.avatar.support.constants.EMessage;
import org.dayup.avatar.support.exceptions.CoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vue/docs/doc")
public class DocumentApi extends BaseApi {


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
            return wrapError();
        }
    }


}
