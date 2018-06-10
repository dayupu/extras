package org.dayup.avatar.service.docs;

import org.dayup.avatar.model.vo.DocLibraryVo;
import org.dayup.avatar.model.vo.DocumentVo;

import java.util.List;


public interface IDocumentService {

    DocumentVo save(DocumentVo documentVo);

    DocumentVo update(DocumentVo documentVo);

    DocumentVo getDocument(Long id);

    List<DocumentVo> getDocumentList(Long libId);
}
