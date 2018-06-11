package org.dayup.avatar.service.docs.impl;

import org.dayup.avatar.jpa.entity.Document;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.jpa.repository.DocumentRepo;
import org.dayup.avatar.model.define.PageResult;
import org.dayup.avatar.model.parser.DocumentParser;
import org.dayup.avatar.model.vo.DocumentQuery;
import org.dayup.avatar.model.vo.DocumentVo;
import org.dayup.avatar.service.docs.IDocumentService;
import org.dayup.avatar.support.common.IDSecure;
import org.dayup.avatar.support.constants.EMessage;
import org.dayup.avatar.support.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class DocumentServiceImpl implements IDocumentService {

    @Autowired
    private DocumentRepo documentRepo;

    private DocumentParser documentParser = new DocumentParser();

    @Transactional
    @Override
    public DocumentVo save(DocumentVo documentVo) {

        Document document = null;
        Long libId = IDSecure.decode(documentVo.getLibId());
        if (StringUtils.isEmpty(documentVo.getId())) {
            Document lastDoc = documentRepo.findTop1ByLibIdOrderBySequenceDesc(libId);
            Integer sequence = 1;
            if (lastDoc != null && lastDoc.getSequence() != null) {
                sequence = lastDoc.getSequence() + 1;
            }
            document = new Document();
            document.setStatus(EDataStatus.INIT);
            document.setTitle(documentVo.getTitle());
            document.setLibId(libId);
            document.setSequence(sequence);
            document.setContent(documentVo.getContent());
            document.setCreatedOn(LocalDateTime.now());
            document.setUpdatedOn(LocalDateTime.now());
            document = documentRepo.save(document);
        } else {
            document = documentRepo.findById(IDSecure.decode(documentVo.getId())).get();
            if (document == null) {
                throw new BusinessException(EMessage.DOC01003);
            }
            document.setTitle(documentVo.getTitle());
            document.setContent(documentVo.getContent());
            document.setUpdatedOn(LocalDateTime.now());
        }

        return documentParser.toVo(document, new DocumentVo());
    }

    @Override
    public DocumentVo update(DocumentVo documentVo) {
        return null;
    }

    @Transactional
    @Override
    public DocumentVo getDocument(Long id) {
        Document document = documentRepo.findById(id).get();
        return documentParser.toVo(document, new DocumentVo());
    }

    @Transactional
    @Override
    public PageResult<DocumentVo> search(DocumentQuery query) {
        Long libId = IDSecure.decode(query.getLibId());
        Page<Document> result = documentRepo.findByLibIdAndStatus(libId, EDataStatus.INIT, query.page());
        return documentParser.toVoPageResult(result, DocumentVo.class);
    }
}
