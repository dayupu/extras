
package org.dayup.avatar.service.docs;

import org.dayup.avatar.base.refs.page.PageResult;
import org.dayup.avatar.web.model.vo.DocumentQuery;
import org.dayup.avatar.web.model.vo.DocumentVo;
import org.dayup.avatar.web.model.vo.SegTree;
import org.dayup.avatar.web.model.vo.SegmentVo;

import java.util.List;

public interface ISegmentService {

    SegmentVo save(SegmentVo segmentVo);

}
