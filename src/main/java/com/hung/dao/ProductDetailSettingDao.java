package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hung.dto.ProductDetailSettingDto;


@Repository
@Transactional
public class ProductDetailSettingDao extends JdbcDaoSupport implements IProductDetailSettingDao {
	
	@Autowired
	public ProductDetailSettingDao(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	@Override
	public List<ProductDetailSettingDto> getProductDetail(){
		String sql = "Select * from product_detail_setting ";
		List<ProductDetailSettingDto> detailsetting = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(ProductDetailSettingDto.class));
		return detailsetting;
	}

}
