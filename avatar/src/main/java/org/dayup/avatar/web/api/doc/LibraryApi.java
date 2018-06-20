package org.dayup.avatar.web.api.doc;

import org.dayup.avatar.model.define.ResponseInfo;
import org.dayup.avatar.model.vo.LibraryQuery;
import org.dayup.avatar.model.vo.LibraryVo;
import org.dayup.avatar.service.docs.ILibraryService;
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
@RequestMapping("/vue/docs/lib")
public class LibraryApi extends BaseApi {

    @Autowired
    private ILibraryService libraryService;

    @Autowired
    private IDocumentService documentService;

    @GetMapping("/browse")
    public ResponseInfo get(@RequestParam("id") String encodeId) {

        try {
            Long id = IDSecure.decode(encodeId);
            LibraryVo libraryVo = libraryService.getLibrary(id);
            return wrapSuccess(libraryVo);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            return wrapError();
        }
    }

    @PostMapping("/create")
    public ResponseInfo create(@RequestBody LibraryVo libraryVo) {
        try {
            libraryService.save(libraryVo);
            return wrapResponse(EMessage.SAVE_SUCCESS);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            return wrapError();
        }
    }


    @PostMapping("/search")
    public ResponseInfo search(@RequestBody LibraryQuery query) {
        return wrapSuccess(libraryService.search(query));
    }

}
