package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hung.common.SqlQueryDefineIf;
import com.hung.dao.mapper.ProductMapper;
import com.hung.dto.ProductDto;

@Repository
@Transactional
public class ProductInfoDao extends JdbcDaoSupport implements IProductInfoDao {

	@Autowired
	public ProductInfoDao(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	// test this Product Dao
	@Override
	public ProductDto getProduct(String ProductId) {
		// Auto-generated method stub
		String sql = "Select name, price, st_date, status, image, color, sale, brand, origin, made_in, size from Product where p.ProductId = ? ";

		Object[] params = new Object[] { ProductId };
		ProductMapper mapper = new ProductMapper();
		try {
			ProductDto product = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return product;
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


	@Override
	public List<ProductDto> getListProduct(String idType) {
		String sql = "Select * from Product where p.idType= ? ";

		Object[] params = new Object[] {idType};
		// ProductMapper mapper = new ProductMapper();
		List<ProductDto> product = this.getJdbcTemplate().queryForList(sql, params, ProductDto.class);
		return product;

	}

	@Override
	public int addProduct(ProductDto productDto) {
		String sql = SqlQueryDefineIf.PRODUCT_ADD;
		Object product = new Object[] {productDto.getProductId(), productDto.getName(), 
				productDto.getPrice(), productDto.getStDate(), productDto.getIdType(),
				productDto.getStatus(), productDto.getImg(), productDto.getColor(),
				productDto.getColor(), productDto.getSale(), productDto.getBrand(),
				productDto.getOrigin(), productDto.getMadeIn(), productDto.getSize(),
				productDto.getuTimestamp(), productDto.getIdpost()};

		return this.getJdbcTemplate().update(sql, product );		
	}

	@Override
	public int editProduct(ProductDto productDto) {
		String sql = SqlQueryDefineIf.PRODUCT_UPDATE;
		Object product = new Object[] {productDto.getProductId(), productDto.getName(), 
				productDto.getPrice(), productDto.getStDate(), productDto.getIdType(),
				productDto.getStatus(), productDto.getImg(), productDto.getColor(),
				productDto.getColor(), productDto.getSale(), productDto.getBrand(),
				productDto.getOrigin(), productDto.getMadeIn(), productDto.getSize(),
				productDto.getuTimestamp(), productDto.getIdpost()};
		
		return this.getJdbcTemplate().update(sql, product);
	}

	@Override
	public int deleteProduct(ProductDto ProductDto) {
		String sql = SqlQueryDefineIf.PRODUCT_DELETE;
		Object product = new Object[] {ProductDto.getProductId()};
		return this.getJdbcTemplate().update(sql, product);
				
	}

//	@Override
//	public List<ProductDto> getProduct() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
