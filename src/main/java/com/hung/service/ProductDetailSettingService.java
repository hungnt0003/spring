package com.hung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hung.common.utils.CommonStringUtils;
import com.hung.dao.IProductDetailSettingDao;
import com.hung.dto.ProductDetailSettingDto;

@Service
public class ProductDetailSettingService implements IProductDetailSettingService {

	@Autowired
	public IProductDetailSettingDao productDetailSettingDao;

	@Override
	public List<ProductDetailSettingDto> getProductDetailSetting() {
		List<ProductDetailSettingDto> dto = productDetailSettingDao.getProductDetailSetting();
		for (ProductDetailSettingDto productDetailSetting : dto) {
			convertView(productDetailSetting);
		}
		return dto;
	}

	private void convertView(ProductDetailSettingDto dto) {
		dto.setBrand(convertViewDetail(dto.getBrand()));
		dto.setColor(convertViewDetail(dto.getColor()));
		dto.setBrand(convertViewDetail(dto.getBrand()));
		dto.setImg(convertViewDetail(dto.getImg()));
		dto.setMadeIn(convertViewDetail(dto.getMadeIn()));
		dto.setOrigin(convertViewDetail(dto.getOrigin()));
		dto.setPrice(convertViewDetail(dto.getPrice()));
		dto.setSale(convertViewDetail(dto.getSale()));
		dto.setSize(convertViewDetail(dto.getSize()));
		dto.setStatus(convertViewDetail(dto.getStatus()));
		dto.setTypeId(convertViewDetail(dto.getTypeId()));
	}

	private String convertViewDetail(String input) {
		if (CommonStringUtils.isNull(input)) {
			return "-";
		} else {
			return "●";
		}
	}
}
