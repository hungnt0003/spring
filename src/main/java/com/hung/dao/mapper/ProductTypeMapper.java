package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hung.dto.ProductTypeDto;

public class ProductTypeMapper implements RowMapper<ProductTypeDto> {

	@Override
	public ProductTypeDto mapRow(ResultSet resultset, int rowNum) throws SQLException {

		String idtype = resultset.getString("id_Type");
		String idproduct = resultset.getString("id_Product");
		// String typename = resultset.getString("typeName");
		String status = resultset.getString("status");

		return new ProductTypeDto(idtype, idproduct, status);
	}

}
