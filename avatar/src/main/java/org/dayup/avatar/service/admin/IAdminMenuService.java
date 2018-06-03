package org.dayup.avatar.service.admin;

import org.dayup.avatar.model.vo.MenuVo;

import java.util.List;

public interface IAdminMenuService {

    List<MenuVo> getMenuTree();
}
