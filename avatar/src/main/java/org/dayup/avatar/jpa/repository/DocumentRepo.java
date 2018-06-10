package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.Document;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DocumentRepo extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {

    List<Document> findByLibIdAndStatus(Long libId, EDataStatus status);
}
