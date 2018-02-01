package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hung.dto.ProductTypeDto;

@Repository
@Transactional
public class ProductTypeDao extends JdbcDaoSupport implements IProductTypeDao {
	
	@Autowired
    public ProductTypeDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

	//@Override
	/*public ProductTypeDto getProductType(String idType) {
		String sql = "Select * from product_type where id_type = ? ";
		Object[] params = new Object[] {idType};
		try {
			ProductTypeDto productType = this.getJdbcTemplate().queryForObject(sql, params, ProductTypeDto.class);
			return productType;
		} catch (EmptyResultDataAccessException e){
			System.out.println(e);
			return null;
		}
	}*/
	
	@Override
	public List<ProductTypeDto> getProductType(){
		String sql = "Select * from product_type";
		List<ProductTypeDto> productType = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProductTypeDto.class));
		return productType;
	}

}
