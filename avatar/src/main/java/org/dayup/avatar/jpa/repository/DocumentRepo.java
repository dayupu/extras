package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.Document;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.support.fool.FoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DocumentRepo extends FoolRepository<Document, Long> {

    List<Document> findByLibIdAndStatus(Long libId, EDataStatus status);

    Document findTop1ByLibIdOrderBySequenceDesc(Long libId);

    Page<Document> findByLibIdAndStatus(Long libId, EDataStatus status, Pageable pageable);
}
