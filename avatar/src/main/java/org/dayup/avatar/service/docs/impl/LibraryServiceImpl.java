package org.dayup.avatar.service.docs.impl;

import org.dayup.avatar.jpa.entity.Library;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.jpa.repository.LibraryRepo;
import org.dayup.avatar.model.define.page.PageResult;
import org.dayup.avatar.model.parser.DocLibraryParser;
import org.dayup.avatar.model.vo.LibraryQuery;
import org.dayup.avatar.model.vo.LibraryVo;
import org.dayup.avatar.service.docs.ILibraryService;
import org.dayup.avatar.support.constants.EMessage;
import org.dayup.avatar.support.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LibraryServiceImpl implements ILibraryService {

    @Autowired
    private LibraryRepo libraryRepo;

    private DocLibraryParser docLibraryParser = new DocLibraryParser();


    @Transactional
    @Override
    public LibraryVo getLibrary(Long id) {

        Library library = libraryRepo.findById(id).get();
        if (library == null) {
            throw new BusinessException(EMessage.DOC01002);
        }

        return docLibraryParser.toVo(library, new LibraryVo());
    }

    @Transactional
    @Override
    public void save(LibraryVo libraryVo) {

        Library library = libraryRepo.findByNameAndStatus(libraryVo.getName(), EDataStatus.INIT);
        if (library != null) {
            throw new BusinessException(EMessage.DOC01001);
        }

        Library lastLib = libraryRepo.findTop1ByStatusOrderBySequenceDesc(EDataStatus.INIT);
        Integer sequence = 1;
        if (lastLib != null && lastLib.getSequence() != null) {
            sequence = lastLib.getSequence() + 1;
        }

        library = new Library();
        library.setName(libraryVo.getName());
        library.setSequence(sequence);
        library.setDescription(libraryVo.getDescription());
        library.setStatus(EDataStatus.INIT);
        library.setCreatedOn(LocalDateTime.now());
        library.setUpdatedOn(LocalDateTime.now());
        libraryRepo.save(library);
    }

    @Override
    public LibraryVo update(LibraryVo libraryVo) {
        return null;
    }

    @Transactional
    @Override
    public PageResult<LibraryVo> search(LibraryQuery query) {
        Page<Library> result = libraryRepo.findByStatus(EDataStatus.INIT, query.page());
        return docLibraryParser.toVoPageResult(result, LibraryVo.class);
    }
}
