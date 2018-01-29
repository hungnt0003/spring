package com.hung.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hung.dto.ProductDto;

public class ProductMapper implements RowMapper<ProductDto> {
	@Override
	public ProductDto mapRow(ResultSet resultset, int rowNum) throws SQLException {
		String productName = resultset.getString("Productname");
//		String password = resultset.getString("Password");

		return new ProductDto(productName);
	}
}
