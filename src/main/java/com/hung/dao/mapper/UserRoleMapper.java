/**
 * 
 */
package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hung.dto.UserRoleDto;

/**
 * @author Hung
 *
 */
public class UserRoleMapper implements RowMapper<UserRoleDto> {

	@Override
	public UserRoleDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		String userName = resultSet.getString("USERNAME");
		String roleId = resultSet.getString("ROLE_ID");
		String roleName = resultSet.getString("ROLE_NAME");

		UserRoleDto dto = new UserRoleDto(roleId, userName, roleName);
		return dto;
	}

}
