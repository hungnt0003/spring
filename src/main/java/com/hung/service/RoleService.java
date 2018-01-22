package com.hung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hung.dao.IRoleDao;
import com.hung.dto.RoleDto;

/**
 * クラスタイトル(ピリオド削除厳禁).
 *
 * <pre>
 * 内容, 使用例など
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class RoleService implements IRoleService {

    /** IRoleDao. */
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<RoleDto> getRoles() {
        return roleDao.getRoles();
    }

}
