package org.dayup.avatar.web.api;

import org.dayup.avatar.model.define.ResponseInfo;
import org.dayup.avatar.model.vo.DocLibraryVo;
import org.dayup.avatar.model.vo.DocumentVo;
import org.dayup.avatar.service.docs.IDocLibraryService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/vue/docs/lib")
public class DocLibraryApi extends BaseApi {

    @Autowired
    private IDocLibraryService libraryService;

    @Autowired
    private IDocumentService documentService;

    @GetMapping("/browse")
    public ResponseInfo get(@RequestParam("id") String encodeId) {

        try {
            Long id = IDSecure.decode(encodeId);
            DocLibraryVo docLibraryVo = libraryService.getLibrary(id);
            return wrapSuccess(docLibraryVo);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            return wrapError();
        }
    }

    @GetMapping("/detail")
    public ResponseInfo getDetail(@RequestParam("id") String idStr) {

        try {
            Long id = IDSecure.decode(idStr);
            List<DocumentVo> documentVos = documentService.getDocumentList(id);
            Map<String, Object> result = new HashMap<>();
            result.put("documents", documentVos);
            return wrapSuccess(result);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            return wrapError();
        }
    }

    @PostMapping("/create")
    public ResponseInfo create(@RequestBody DocLibraryVo docLibraryVo) {
        try {
            libraryService.save(docLibraryVo);
            return wrapResponse(EMessage.SAVE_SUCCESS);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            return wrapError();
        }
    }


    @PostMapping("/list")
    public ResponseInfo list() {
        try {
            List<DocLibraryVo> voList = libraryService.getLibraryList();
            return wrapSuccess(voList);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            return wrapError();
        }
    }

}
