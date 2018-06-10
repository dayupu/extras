package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.DocLibrary;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DocLibraryRepo extends JpaRepository<DocLibrary, Long>, JpaSpecificationExecutor<DocLibrary> {


    DocLibrary findByNameAndStatus(String name, EDataStatus status);

    List<DocLibrary> findByStatus(EDataStatus status);

}
