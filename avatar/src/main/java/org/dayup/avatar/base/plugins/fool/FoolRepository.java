
package org.dayup.avatar.base.plugins.fool;

import org.dayup.avatar.base.plugins.fool.model.FoolCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;


@NoRepositoryBean
public interface FoolRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    List<T> search(Collection<FoolCondition> conditions);

    Page<T> search(Collection<FoolCondition> conditions, Pageable pageable);

}
