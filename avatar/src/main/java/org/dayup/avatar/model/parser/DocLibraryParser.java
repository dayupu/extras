package org.dayup.avatar.model.parser;

import org.dayup.avatar.jpa.entity.DocLibrary;
import org.dayup.avatar.model.define.BaseParser;
import org.dayup.avatar.model.vo.DocLibraryVo;
import org.dayup.avatar.support.common.IDSecure;

public class DocLibraryParser extends BaseParser<DocLibrary, DocLibraryVo> {


    @Override
    public DocLibraryVo toVo(DocLibrary entity, DocLibraryVo vo) {
        vo.setId(IDSecure.encode(entity.getId()));
        vo.setName(entity.getName());
        vo.setDescription(entity.getDescription());
        vo.setCreatedOn(entity.getCreatedOn());
        vo.setUpdatedOn(entity.getUpdatedOn());
        return vo;
    }

}
