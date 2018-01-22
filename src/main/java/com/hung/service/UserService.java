package com.hung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hung.common.utils.CommonObjectUtils;
import com.hung.dao.IRoleDao;
import com.hung.dao.IUserInfoDao;
import com.hung.dto.RoleDto;
import com.hung.dto.UserDto;

import javassist.NotFoundException;

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
@Service
public class UserService implements IUserService {

    /** IUserInfoDao. */
    @Autowired
    private IUserInfoDao userInfoDao;
    /** IRoleDao. */
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<UserDto> getUsers() {
        return userInfoDao.getUsers();
    }

    @Override
    public UserDto getFullUser(String userName) throws NotFoundException {
        UserDto user = userInfoDao.getFullUser(userName);
        List<RoleDto> roles = roleDao.getRoles();
        if (CommonObjectUtils.isNullOrEmpty(user)) {
            throw new NotFoundException("UserService#getFullUser UserDto not found");
        }
        user.setRoles(roles);
        return user;
    }

}
