package org.dayup.avatar.service.docs;

import org.dayup.avatar.model.define.page.PageResult;
import org.dayup.avatar.model.vo.DocumentQuery;
import org.dayup.avatar.model.vo.DocumentVo;

import java.util.List;

public interface IDocumentService {

    DocumentVo save(DocumentVo documentVo);

    DocumentVo update(DocumentVo documentVo);

    DocumentVo getDocument(Long id);

    PageResult<DocumentVo> search(DocumentQuery query);

    void drop(List<Long> docIds);

    void move(List<DocumentVo> documentVos);
}
