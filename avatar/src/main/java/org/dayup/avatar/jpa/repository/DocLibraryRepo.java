package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.DocLibrary;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.support.fool.FoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DocLibraryRepo extends FoolRepository<DocLibrary, Long> {

    DocLibrary findByNameAndStatus(String name, EDataStatus status);

    Page<DocLibrary> findByStatus(EDataStatus status, Pageable pageable);

    DocLibrary findTop1ByStatusOrderBySequenceDesc(EDataStatus status);
}
