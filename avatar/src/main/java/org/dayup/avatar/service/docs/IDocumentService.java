package org.dayup.avatar.service.docs;

import org.dayup.avatar.model.define.page.PageResult;
import org.dayup.avatar.model.vo.DocumentQuery;
import org.dayup.avatar.model.vo.DocumentVo;

public interface IDocumentService {

    DocumentVo save(DocumentVo documentVo);

    DocumentVo update(DocumentVo documentVo);

    DocumentVo getDocument(Long id);

    PageResult<DocumentVo> search(DocumentQuery query);
}
