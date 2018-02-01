package com.hung.dao;

import java.util.List;

import com.hung.dto.ProductTypeDto;

public interface IProductTypeDao {
	
//	public ProductTypeDto getProductType(String productType);
	
	public List<ProductTypeDto> getProductType();

}
