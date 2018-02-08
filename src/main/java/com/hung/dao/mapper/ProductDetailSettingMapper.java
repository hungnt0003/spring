package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hung.dto.ProductDetailSettingDto;

public class ProductDetailSettingMapper implements RowMapper<ProductDetailSettingDto> {

	@Override
	public ProductDetailSettingDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		String idtype = rs.getString("Id_Type");
		return new ProductDetailSettingDto(idtype);
	}

}
