package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

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
public class UserMapper implements RowMapper<UserDto> {

    @Override
    public UserDto mapRow(ResultSet resultset, int rowNum) throws SQLException {
        String userName = resultset.getString("Username");
        String password = resultset.getString("Password");

        return new UserDto(userName, password);
    }

}
