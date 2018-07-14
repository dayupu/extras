
package org.dayup.avatar.web.model.parser;

import org.dayup.avatar.base.refs.BaseParser;
import org.dayup.avatar.base.utils.IDSecure;
import org.dayup.avatar.jpa.entity.Segment;
import org.dayup.avatar.web.model.vo.SegmentVo;

public class SegmentParser extends BaseParser<Segment, SegmentVo> {


    @Override
    public SegmentVo toVo(Segment entity, SegmentVo vo) {
        vo.setId(IDSecure.encode(entity.getId()));
        vo.setTitle(entity.getTitle());
        vo.setDocId(IDSecure.encode(entity.getDocId()));
        vo.setType(entity.getType().getConstant());
        return vo;
    }

}
