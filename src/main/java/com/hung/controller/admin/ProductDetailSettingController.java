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
import com.hung.dto.ProductDetailSettingDto;
import com.hung.service.IProductDetailSettingService;

@Controller
public class ProductDetailSettingController extends CommonController {

	@Autowired
	IProductDetailSettingService productDetailSettingService;

	@RequestMapping(value = "/detailsetting", method = RequestMethod.GET)
	public ModelAndView productDetailSetting(Model model) {

		model.addAttribute("loaderWrapper", "common/loader_wrapper");
		model.addAttribute("mainContent", "/screens/products/productDetailSetting.html");

		List<ProductDetailSettingDto> productDetailSetting = productDetailSettingService.getProductDetailSetting();

		model.addAttribute("productdetailsetting", productDetailSetting);
		return new CommonModelAndView();
	}

}
