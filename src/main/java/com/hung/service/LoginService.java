package com.hung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class LoginService implements ILoginService {

    @Autowired
    IUserInfoDao loginDao;

    @Override
    public UserDto getUser(String userName) {
        return loginDao.getUser(userName);
    }

    @Override
    public List<String> getUserRoles(String userName) {
        return loginDao.getUserRoles(userName);
    }

}
