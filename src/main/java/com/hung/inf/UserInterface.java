package com.hung.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hung.common.exception.StoreException;
import com.hung.common.utils.CommonObjectUtils;
import com.hung.dao.IRoleDao;
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

	/** UserInfoDao. */
	@Autowired
	private IUserInfoDao userInfoDao;
	/** RoleDao. */
	@Autowired
	private IRoleDao roleDao;

	@Override
	public void storeUser(UserDto userDto) throws StoreException, Exception {

		int result = 0;
		try {
			if (CommonObjectUtils.isNullOrEmpty(userInfoDao.getUser(userDto.getUserName()))) {
				result = userInfoDao.addUser(userDto);
			} else {
				result = userInfoDao.edit(userDto);
			}
		} catch (Exception e) {
			throw new Exception();
		}
		if (result != 1) {
			throw new StoreException();
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
	public void delete(UserDto userDto) {
		userInfoDao.delete(userDto);
	}
}
