package com.hung.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hung.common.ISelectListCreator;
import com.hung.common.exception.StoreException;
import com.hung.common.utils.CommonListUtils;
import com.hung.common.utils.CommonObjectUtils;
import com.hung.dto.RoleDto;
import com.hung.dto.UserDto;
import com.hung.dto.UserRoleDto;
import com.hung.inf.IRoleInterface;
import com.hung.inf.IUserInterface;
import com.hung.inf.IUserRoleInterface;

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
	/** SelectListCreator. */
	@Autowired
	private ISelectListCreator selectListCreator;
	/** UserRoleInterface. */
	@Autowired
	private IUserRoleInterface userRoleInterface;

	@Override
	public List<UserDto> search() {
		return userInterface.getUsers();
	}

	@Override
	public UserDto getUser(String userName) throws NotFoundException {
		UserDto user = userInterface.getUser(userName);
		if (CommonObjectUtils.isNullOrEmpty(user)) {
			throw new NotFoundException("UserService#getFullUser UserDto not found");
		}
		UserRoleDto userRoleDto = userRoleInterface.getMaxRole(userName);
		List<UserRoleDto> userRoleDtoList = userRoleInterface.getUserRoles(userName);

		List<RoleDto> roleDtoList = new ArrayList<>();
		if (CommonListUtils.isNotNullOrEmpty(userRoleDtoList)) {
			for (UserRoleDto dto : userRoleDtoList) {
				roleDtoList.add(dto.convertRoleDto());
			}
		}
		user.setRoles(roleDtoList);
		user.setMaxRole(userRoleDto.convertRoleDto());
		user.setRoleList(selectListCreator.getRoleList());
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
