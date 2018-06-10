package org.dayup.avatar.service.docs.impl;

import org.dayup.avatar.jpa.entity.DocLibrary;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.jpa.repository.DocLibraryRepo;
import org.dayup.avatar.model.parser.DocLibraryParser;
import org.dayup.avatar.model.vo.DocLibraryVo;
import org.dayup.avatar.service.docs.IDocLibraryService;
import org.dayup.avatar.support.constants.EMessage;
import org.dayup.avatar.support.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocLibraryServiceImpl implements IDocLibraryService {

    @Autowired
    private DocLibraryRepo docLibraryRepo;

    private DocLibraryParser docLibraryParser = new DocLibraryParser();


    @Transactional
    @Override
    public DocLibraryVo getLibrary(Long id) {

        DocLibrary docLibrary = docLibraryRepo.findById(id).get();
        if(docLibrary == null){
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

        docLibrary = new DocLibrary();
        docLibrary.setName(docLibraryVo.getName());
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
    public List<DocLibraryVo> getLibraryList() {

        List<DocLibrary> docLibraries = docLibraryRepo.findByStatus(EDataStatus.INIT);

        return docLibraryParser.toVoList(docLibraries, DocLibraryVo.class);
    }
}
