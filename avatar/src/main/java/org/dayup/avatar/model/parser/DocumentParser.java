package org.dayup.avatar.model.parser;

import org.dayup.avatar.jpa.entity.Document;
import org.dayup.avatar.model.define.BaseParser;
import org.dayup.avatar.model.vo.DocumentVo;
import org.dayup.avatar.support.common.IDSecure;

public class DocumentParser extends BaseParser<Document, DocumentVo> {


    @Override
    public DocumentVo toVo(Document entity, DocumentVo vo) {
        vo.setId(IDSecure.encode(entity.getId()));
        vo.setLibId(IDSecure.encode(entity.getLibId()));
        vo.setTitle(entity.getTitle());
        vo.setSequence(entity.getSequence());
        vo.setContent(entity.getContent());
        vo.setCreatedOn(entity.getCreatedOn());
        vo.setUpdatedOn(entity.getUpdatedOn());
        return vo;
    }

}
