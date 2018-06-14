package org.dayup.avatar.service.docs;

import org.dayup.avatar.model.define.page.PageResult;
import org.dayup.avatar.model.vo.DocLibraryQuery;
import org.dayup.avatar.model.vo.DocLibraryVo;

public interface IDocLibraryService {

    void save(DocLibraryVo docLibraryVo);

    DocLibraryVo update(DocLibraryVo docLibraryVo);

    DocLibraryVo getLibrary(Long id);

    PageResult<DocLibraryVo> search(DocLibraryQuery query);
}
