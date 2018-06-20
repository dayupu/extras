package org.dayup.avatar.web.api.doc;

import org.dayup.avatar.model.define.ResponseInfo;
import org.dayup.avatar.service.docs.IDataModelService;
import org.dayup.avatar.support.BaseApi;
import org.dayup.avatar.support.common.IDSecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vue/docs/model")
public class DataModelApi extends BaseApi {

    @Autowired
    private IDataModelService dataModelService;

    @GetMapping("/types")
    public ResponseInfo search(@RequestParam("libId") String libId) {
        return wrapSuccess(dataModelService.getDataTypesWithLibId(IDSecure.decode(libId)));
    }

}
