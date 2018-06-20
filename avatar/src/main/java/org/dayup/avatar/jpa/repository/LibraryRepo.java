package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.Library;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.support.fool.FoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LibraryRepo extends FoolRepository<Library, Long> {

    Library findByNameAndStatus(String name, EDataStatus status);

    Page<Library> findByStatus(EDataStatus status, Pageable pageable);

    Library findTop1ByStatusOrderBySequenceDesc(EDataStatus status);
}
