package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hung.dto.RoleDto;

/**
 * 
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
public class RoleMapper implements RowMapper<RoleDto> {

    @Override
    public RoleDto mapRow(ResultSet resultset, int rowNum) throws SQLException {
        String roleId = resultset.getString("ROLE_ID");
        String roleName = resultset.getString("ROLE_NAME");
        return new RoleDto(roleId, roleName);
    }

}
