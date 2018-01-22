package com.hung.dao;

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
public interface IUserInfoDao {

    public UserDto getUser(String userName);

    // [USER,AMIN,..]
    public List<String> getUserRoles(String userName);

    public List<UserDto> getUsers();

    public UserDto getFullUser(String userName);

    public int addUser(UserDto userDto);

    public int edit(UserDto userDto);

    public int delete(UserDto userDto);
}
