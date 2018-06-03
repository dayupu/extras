package org.dayup.avatar.jpa.repository;

import org.dayup.avatar.jpa.entity.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminMenuRepository extends JpaRepository<AdminMenu, Long>, JpaSpecificationExecutor<AdminMenu> {


    @Query("from AdminMenu where level = 0")
    List<AdminMenu> findRootMenus();

}
