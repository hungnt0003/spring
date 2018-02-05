package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
        String enabled = resultset.getString("ENABLED");
        String img = resultset.getString("IMG");
        String firstName = resultset.getString("FIRSTNAME");
        String lastName = resultset.getString("LASTNAME");
        Date birth = resultset.getDate("BIRTH");
        Date stDate = resultset.getDate("STDATE");
        Date edDate = resultset.getDate("EDDATE");
        String sex = resultset.getString("SEX");
        String thumbnail = resultset.getString("THUMB");
        String email = resultset.getString("EMAIL");

        UserDto dto = new UserDto(userName, password);
        dto.setEnabled(enabled.equals("0") ? false : true);
        dto.setAvartaPath(img);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setBirth(birth);
        dto.setStDate(stDate);
        dto.setEdDate(edDate);
        dto.setSex(sex);
        dto.setThumbPath(thumbnail);
        dto.setEmail(email);

        return dto;
    }

}
