package com.hung.service;

import java.util.List;

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
public interface IUserService {

    List<UserDto> getUsers();

    UserDto getFullUser(String userName) throws NotFoundException;
}
