
package org.dayup.avatar.web.model.parser;

import org.dayup.avatar.jpa.entity.Document;
import org.dayup.avatar.base.refs.BaseParser;
import org.dayup.avatar.web.model.vo.DocumentVo;
import org.dayup.avatar.base.utils.IDSecure;

public class DocumentParser extends BaseParser<Document, DocumentVo> {


    @Override
    public DocumentVo toVo(Document entity, DocumentVo vo) {
        vo.setId(IDSecure.encode(entity.getId()));
        vo.setTitle(entity.getTitle());
        vo.setSequence(entity.getSequence());
        vo.setDescription(entity.getDescription());
        vo.setCreatedOn(entity.getCreatedOn());
        vo.setUpdatedOn(entity.getUpdatedOn());
        return vo;
    }

}
