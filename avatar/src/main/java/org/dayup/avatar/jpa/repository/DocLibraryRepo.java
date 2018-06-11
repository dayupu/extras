package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.DocLibrary;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DocLibraryRepo extends JpaRepository<DocLibrary, Long>, JpaSpecificationExecutor<DocLibrary> {

    DocLibrary findByNameAndStatus(String name, EDataStatus status);

    Page<DocLibrary> findByStatus(EDataStatus status, Pageable pageable);

    DocLibrary findTop1ByStatusOrderBySequenceDesc(EDataStatus status);
}
