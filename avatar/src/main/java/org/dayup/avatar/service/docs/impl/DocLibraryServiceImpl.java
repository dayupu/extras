package org.dayup.avatar.service.docs.impl;

import org.dayup.avatar.jpa.entity.DocLibrary;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.jpa.repository.DocLibraryRepo;
import org.dayup.avatar.model.define.page.PageResult;
import org.dayup.avatar.model.parser.DocLibraryParser;
import org.dayup.avatar.model.vo.DocLibraryQuery;
import org.dayup.avatar.model.vo.DocLibraryVo;
import org.dayup.avatar.service.docs.IDocLibraryService;
import org.dayup.avatar.support.constants.EMessage;
import org.dayup.avatar.support.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DocLibraryServiceImpl implements IDocLibraryService {

    @Autowired
    private DocLibraryRepo docLibraryRepo;

    private DocLibraryParser docLibraryParser = new DocLibraryParser();


    @Transactional
    @Override
    public DocLibraryVo getLibrary(Long id) {

        DocLibrary docLibrary = docLibraryRepo.findById(id).get();
        if (docLibrary == null) {
            throw new BusinessException(EMessage.DOC01002);
        }

        return docLibraryParser.toVo(docLibrary, new DocLibraryVo());
    }

    @Transactional
    @Override
    public void save(DocLibraryVo docLibraryVo) {

        DocLibrary docLibrary = docLibraryRepo.findByNameAndStatus(docLibraryVo.getName(), EDataStatus.INIT);
        if (docLibrary != null) {
            throw new BusinessException(EMessage.DOC01001);
        }

        DocLibrary lastLib = docLibraryRepo.findTop1ByStatusOrderBySequenceDesc(EDataStatus.INIT);
        Integer sequence = 1;
        if (lastLib != null && lastLib.getSequence() != null) {
            sequence = lastLib.getSequence() + 1;
        }

        docLibrary = new DocLibrary();
        docLibrary.setName(docLibraryVo.getName());
        docLibrary.setSequence(sequence);
        docLibrary.setDescription(docLibraryVo.getDescription());
        docLibrary.setStatus(EDataStatus.INIT);
        docLibrary.setCreatedOn(LocalDateTime.now());
        docLibrary.setUpdatedOn(LocalDateTime.now());
        docLibraryRepo.save(docLibrary);
    }

    @Override
    public DocLibraryVo update(DocLibraryVo docLibraryVo) {
        return null;
    }

    @Transactional
    @Override
    public PageResult<DocLibraryVo> search(DocLibraryQuery query) {
        Page<DocLibrary> result = docLibraryRepo.findByStatus(EDataStatus.INIT, query.page());
        return docLibraryParser.toVoPageResult(result, DocLibraryVo.class);
    }
}
