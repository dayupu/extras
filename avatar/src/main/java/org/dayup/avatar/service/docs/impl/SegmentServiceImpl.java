package org.dayup.avatar.service.docs.impl;

import org.dayup.avatar.base.utils.IDSecure;
import org.dayup.avatar.jpa.entity.Document;
import org.dayup.avatar.jpa.entity.Segment;
import org.dayup.avatar.jpa.enums.ESegmentType;
import org.dayup.avatar.jpa.repository.DocumentRepo;
import org.dayup.avatar.jpa.repository.SegmentRepo;
import org.dayup.avatar.service.docs.ISegmentService;
import org.dayup.avatar.web.model.parser.SegmentParser;
import org.dayup.avatar.web.model.vo.SegmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class SegmentServiceImpl implements ISegmentService {


    @Autowired
    private SegmentRepo segmentRepo;

    @Autowired
    private DocumentRepo documentRepo;

    private SegmentParser segmentParser = new SegmentParser();

    @Transactional
    @Override
    public SegmentVo save(SegmentVo segmentVo) {

        Long docId = IDSecure.decode(segmentVo.getDocId());
        Document document = documentRepo.getOne(docId);
        Segment segment = null;
        if (StringUtils.isEmpty(segmentVo.getId())) {
            segment = new Segment();
            segment.setDocument(document);
            segment.setType(ESegmentType.fromConstant(segmentVo.getType()));
            segment.setTitle(segmentVo.getTitle());
            segment.setParent(getParent(segmentVo.getPid()));
            segment = segmentRepo.save(segment);
        }
        return segmentParser.toVo(segment, new SegmentVo());
    }

    private Segment getParent(String encodePid) {
        if ("home".equals(encodePid)) {
            return null;
        }
        Long pid = IDSecure.decode(encodePid);
        return segmentRepo.getOne(pid);
    }
}
