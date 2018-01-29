/**
 *
 */
package com.hung.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hung.dao.IProductInfoDao;
import com.hung.dto.ProductDTO;

/**
 * @author WBC01
 *
 */
public class ProductInterface implements IProductInterface {

	@Autowired
	private IProductInfoDao productInfoDao;

	@Override
	public List<ProductDTO> gerProduct() {
		// TODO Auto-generated method stub
		return productInfoDao.getProduct();
	}

}
