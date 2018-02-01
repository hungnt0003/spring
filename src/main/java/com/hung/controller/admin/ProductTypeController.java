package com.hung.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.CommonController;
import com.hung.common.CommonModelAndView;
import com.hung.dao.IProductTypeDao;
import com.hung.dao.ProductDetailSettingDao;
import com.hung.dto.ProductDetailSettingDto;
import com.hung.dto.ProductTypeDto;

@Controller
public class ProductTypeController extends CommonController{
	
	@Autowired
	private IProductTypeDao productTypeDao;
	private ProductDetailSettingDao productdetailsetting;
	
	@RequestMapping(value = "/producttype", method = RequestMethod.GET)
	public ModelAndView productType(Model model) {
		
		model.addAttribute("mainContent", "screens/products/productType");
		CommonModelAndView mv = new CommonModelAndView();
		List<ProductTypeDto> productType = productTypeDao.getProductType();
//		try {
//			List<ProductDetailSettingDto> setting = productdetailsetting.getProductDetail();
//			model.addAttribute("detailsetting", setting);
//		} catch (NullPointerException e) {
//			System.out.println(e);
//		}
		model.addAttribute("producttype", productType);
		
		return mv;
	}

}
