package org.dayup.avatar.service.admin.impl;

import org.dayup.avatar.jpa.entity.AdminMenu;
import org.dayup.avatar.jpa.repository.AdminMenuRepository;
import org.dayup.avatar.model.vo.MenuVo;
import org.dayup.avatar.service.admin.IAdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminMenuService implements IAdminMenuService {

    @Autowired
    private AdminMenuRepository menuRepository;

    @Override
    @Transactional
    public List<MenuVo> getMenuTree() {

        List<MenuVo> menuVos = new ArrayList<>();
        List<AdminMenu> menus = menuRepository.findRootMenus();
        return null;
    }
}
