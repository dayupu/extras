
package org.dayup.avatar.web.api.doc;

import org.dayup.avatar.base.exceptions.CoreException;
import org.dayup.avatar.base.refs.EMessage;
import org.dayup.avatar.base.refs.ResponseInfo;
import org.dayup.avatar.service.docs.IDataModelService;
import org.dayup.avatar.base.refs.BaseApi;
import org.dayup.avatar.base.utils.IDSecure;
import org.dayup.avatar.web.model.vo.DataClassVo;
import org.dayup.avatar.web.model.vo.DocumentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/vue/docs/model")
public class DataModelApi extends BaseApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataModelApi.class);

    @Autowired
    private IDataModelService dataModelService;

    @GetMapping("/types")
    public ResponseInfo getTypes(@RequestParam("libId") String libId) {
        return wrapSuccess(dataModelService.getDataTypesWithLibId(IDSecure.decode(libId)));
    }

    @GetMapping("/classes")
    public ResponseInfo search(@RequestParam("libId") String libId) {
        return wrapSuccess(dataModelService.getDataClassListWithLibId(IDSecure.decode(libId)));
    }

    @PostMapping("/save")
    public ResponseInfo create(@RequestBody DataClassVo dataClassVo) {
        try {
            dataClassVo = dataModelService.saveClass(dataClassVo);
            return wrapResponse(EMessage.SAVE_SUCCESS, dataClassVo);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return wrapError();
        }
    }

    @GetMapping("/browse")
    public ResponseInfo browse(@RequestParam("id") String id) {
        try {
            DataClassVo dataClassVo = dataModelService.getClass(IDSecure.decode(id));
            return wrapResponse(EMessage.SAVE_SUCCESS, dataClassVo);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return wrapError();
        }
    }

    @PostMapping("/drop")
    public ResponseInfo drop(@RequestBody DataClassVo classVo) {
        try {
            dataModelService.drop(IDSecure.decode(classVo.getId()));
            return wrapResponse(EMessage.DROP_SUCCESS);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return wrapError();
        }
    }

}
