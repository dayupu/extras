package org.dayup.avatar.service.docs;

import org.dayup.avatar.model.vo.DocLibraryVo;

import java.util.List;


public interface IDocLibraryService {

    void save(DocLibraryVo docLibraryVo);

    DocLibraryVo update(DocLibraryVo docLibraryVo);

    DocLibraryVo getLibrary(Long id);

    List<DocLibraryVo> getLibraryList();
}
