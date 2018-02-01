package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hung.dto.SystemPropertiesDto;

/**
 * 
 * This method mapping between SystemPropertiesDto and database.
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class SystemPropertiesMapper implements RowMapper<SystemPropertiesDto> {

    @Override
    public SystemPropertiesDto mapRow(ResultSet resultset, int rowNum) throws SQLException {
        String sysName = resultset.getString("SYS_NAME");
        String sysValue = resultset.getString("SYS_VALUE");

        SystemPropertiesDto dto = new SystemPropertiesDto();
        dto.setSysName(sysName);
        dto.setSysValue(sysValue);

        return dto;
    }

}
