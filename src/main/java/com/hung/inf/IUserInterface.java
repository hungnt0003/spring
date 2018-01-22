package com.hung.inf;

import java.util.List;

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
public interface IUserInterface {

    void storeUser(UserDto userDto);

    UserDto getUser(String userName);

    List<UserDto> getUsers();

    UserDto getFullUser(String userName);
}
