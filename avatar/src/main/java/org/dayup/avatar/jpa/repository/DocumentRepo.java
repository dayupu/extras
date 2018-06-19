package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.Document;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.support.fool.FoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepo extends FoolRepository<Document, Long> {

    List<Document> findByLibIdAndStatus(Long libId, EDataStatus status);

    Document findTop1ByLibIdOrderBySequenceDesc(Long libId);

    Page<Document> findByLibIdAndStatus(Long libId, EDataStatus status, Pageable pageable);

    @Modifying
    @Query("update Document set status=:status where id in (:ids)")
    void updateStatus(@Param("ids") List<Long> ids, @Param("status") EDataStatus status);

    @Query("from Document where status=:status and id in (:ids)")
    List<Document> findByIds(@Param("ids") List<Long> ids, @Param("status") EDataStatus status);

}
