/**
 *
 */
package com.hung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hung.dao.IProductInfoDao;
import com.hung.dto.ProductDto;

/**
 * @author WBC01
 *
 */
@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductInfoDao productInfoDao;

	@Override
	public List<ProductDto> getProduct() {
		// TODO Auto-generated method stub
		return productInfoDao.getProduct();
	}

}
