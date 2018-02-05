package com.hung.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hung.dao.IRoleDao;
import com.hung.dto.RoleDto;

/**
 * 
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
@Component
public class RoleInterface implements IRoleInterface {

    /** RoleDao. */
    @Autowired
    IRoleDao RoleDao;

    @Override
    public List<RoleDto> getRoles() {
        return RoleDao.getRoles();
    }

}
