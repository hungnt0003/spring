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

    /**
     * 
     * Get user info by user name.
     * 
     * @param userName UserName
     * @return User info
     */
    public UserDto getUser(String userName);

    // [USER,AMIN,..]
    public List<String> getUserRoles(String userName);

    /**
     * 
     * Get all user in db.
     * 
     * @return List of user info
     */
    public List<UserDto> getUsers();

    /**
     * 
     * Add user to db.
     * 
     * @param userDto user info
     * @return count row access.
     */
    public int addUser(UserDto userDto);

    /**
     * 
     * Change info of user by key.
     * 
     * @param userDto user info
     * @return count row access.
     */
    public int edit(UserDto userDto);

    /**
     * 
     * Remove user to db.
     * 
     * @param userDto user info
     * @return count row access.
     */
    public int delete(UserDto userDto);
}
