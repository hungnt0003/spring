package com.hung.dao;

import java.util.List;

import com.hung.dto.ProductDTO;

public interface IProductInfoDao {
	
	public ProductDTO getProduct(String productName);
	
	public List<ProductDTO> getProduct();
	
	public int addProduct(ProductDTO ProductDto);

    public int editProduct(ProductDTO ProductDto);

    public int deleteProduct(ProductDTO ProductDto);

}
