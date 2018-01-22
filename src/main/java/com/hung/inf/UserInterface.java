package com.hung.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hung.common.utils.CommonObjectUtils;
import com.hung.dao.IUserInfoDao;
import com.hung.dto.UserDto;

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
@Component
public class UserInterface implements IUserInterface {

    /** IUserInfoDao. */
    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public void storeUser(UserDto userDto) {
        try {
            if (CommonObjectUtils.isNullOrEmpty(userInfoDao.getUser(userDto.getUserName()))) {
                userInfoDao.addUser(userDto);
            } else {
                userInfoDao.edit(userDto);
            }
        } catch (Exception e) {
            System.out.println("");
        }

    }

    @Override
    public UserDto getUser(String userName) {
        // TODO Auto-Generated Method Stub
        return userInfoDao.getUser(userName);
    }

    @Override
    public List<UserDto> getUsers() {
        // TODO Auto-Generated Method Stub
        return userInfoDao.getUsers();
    }

    @Override
    public UserDto getFullUser(String userName) {
        // TODO Auto-Generated Method Stub
        return userInfoDao.getFullUser(userName);
    }

}
