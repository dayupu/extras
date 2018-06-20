package org.dayup.avatar.service.docs;

import org.dayup.avatar.model.define.page.PageResult;
import org.dayup.avatar.model.vo.LibraryQuery;
import org.dayup.avatar.model.vo.LibraryVo;

public interface ILibraryService {

    void save(LibraryVo libraryVo);

    LibraryVo update(LibraryVo libraryVo);

    LibraryVo getLibrary(Long id);

    PageResult<LibraryVo> search(LibraryQuery query);
}
