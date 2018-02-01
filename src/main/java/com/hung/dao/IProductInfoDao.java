package com.hung.dao;

import java.util.List;

import com.hung.dto.ProductDto;

public interface IProductInfoDao {
	
	public ProductDto getProduct(String productName);
	
	public List<ProductDto> getProduct();
	
	public int addProduct(ProductDto ProductDto);

    public int editProduct(ProductDto ProductDto);

    public int deleteProduct(ProductDto ProductDto);

}
