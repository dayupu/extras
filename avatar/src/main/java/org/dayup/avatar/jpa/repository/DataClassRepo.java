package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.DataClass;
import org.dayup.avatar.jpa.enums.EDataStatus;
import org.dayup.avatar.support.fool.FoolRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DataClassRepo extends FoolRepository<DataClass, Long> {

    List<DataClass> findByLibIdAndStatusOrderBySequence(Long libId, EDataStatus status);


    @Query("from DataClass where status = :status and (isOrigin = true or libId = :libId) order by sequence")
    List<DataClass> findTypesByLibId(@Param("libId") Long libId, @Param("status") EDataStatus status);
}
