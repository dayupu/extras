
package org.dayup.avatar.service.docs;

import org.dayup.avatar.base.refs.page.PageResult;
import org.dayup.avatar.web.model.vo.DocumentQuery;
import org.dayup.avatar.web.model.vo.DocumentVo;

import java.util.List;

public interface IDocumentService {

    DocumentVo save(DocumentVo documentVo);

    DocumentVo update(DocumentVo documentVo);

    DocumentVo getDocument(Long id);

    PageResult<DocumentVo> search(DocumentQuery query);

    void drop(List<Long> docIds);

    void move(List<DocumentVo> documentVos);
}
