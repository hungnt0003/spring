package com.hung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hung.common.exception.StoreException;
import com.hung.common.utils.CommonObjectUtils;
import com.hung.dto.RoleDto;
import com.hung.dto.UserDto;
import com.hung.inf.IRoleInterface;
import com.hung.inf.IUserInterface;

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

    /** UserInterface. */
    @Autowired
    private IUserInterface userInterface;
    /** RoleInterface. */
    @Autowired
    private IRoleInterface roleInterface;

    @Override
    public List<UserDto> search() {
        return userInterface.getUsers();
    }

    @Override
    public UserDto getUser(String userName) throws NotFoundException {
        UserDto user = userInterface.getFullUser(userName);
        List<RoleDto> roles = roleInterface.getRoles();
        if (CommonObjectUtils.isNullOrEmpty(user)) {
            throw new NotFoundException("UserService#getFullUser UserDto not found");
        }
        user.setRoles(roles);
        return user;
    }

    @Override
    public void store(UserDto dto) throws StoreException, Exception {
        userInterface.storeUser(dto);
    }

    @Override
    public void delete(UserDto dto) {
        userInterface.delete(dto);
    }

}
