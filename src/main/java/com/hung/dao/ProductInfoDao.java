package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hung.dto.ProductDto;


@Repository
@Transactional
public class ProductInfoDao extends JdbcDaoSupport implements IProductInfoDao {
	
	@Autowired
    public ProductInfoDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

	@Override
	public ProductDto getProduct(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> getProduct() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public int addProduct(ProductDto ProductDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editProduct(ProductDto ProductDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(ProductDto ProductDto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
