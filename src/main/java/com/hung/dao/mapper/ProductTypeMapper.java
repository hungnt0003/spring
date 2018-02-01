package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hung.dto.ProductTypeDto;

public class ProductTypeMapper implements RowMapper<ProductTypeDto> {
	@Override
	public ProductTypeDto mapRow(ResultSet resultset,int rowNum) throws SQLException {
		String idType = resultset.getString("Typ");
	}

}
