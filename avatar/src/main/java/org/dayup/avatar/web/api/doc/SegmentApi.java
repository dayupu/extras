
package org.dayup.avatar.web.api.doc;

import org.dayup.avatar.base.exceptions.CoreException;
import org.dayup.avatar.base.refs.BaseApi;
import org.dayup.avatar.base.refs.EMessage;
import org.dayup.avatar.base.refs.ResponseInfo;
import org.dayup.avatar.service.docs.ISegmentService;
import org.dayup.avatar.web.model.vo.SegmentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vue/docs/segment")
public class SegmentApi extends BaseApi {


    private static final Logger LOGGER = LoggerFactory.getLogger(SegmentApi.class);

    @Autowired
    private ISegmentService segmentService;



    @PostMapping("/save")
    public ResponseInfo save(@RequestBody SegmentVo segmentVo) {
        try {
            segmentVo = segmentService.save(segmentVo);
            return wrapResponse(EMessage.SAVE_SUCCESS, segmentVo);
        } catch (CoreException e) {
            return wrapException(e);
        } catch (Exception e) {
            LOGGER.error("system error", e);
            return wrapError();
        }
    }

}
