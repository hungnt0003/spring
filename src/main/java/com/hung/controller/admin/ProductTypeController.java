package com.hung.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.CommonController;
import com.hung.common.CommonModelAndView;
import com.hung.dto.ProductTypeDto;
import com.hung.service.IProductService;

@Controller
public class ProductTypeController extends CommonController {

	/** IProductTypeService */
	private IProductService productService;

	@RequestMapping(value = "/producttype", method = RequestMethod.GET)
	public ModelAndView productType(Model model) {

		model.addAttribute("loaderWrapper", "common/loader_wrapper");
		model.addAttribute("mainContent", "screens/products/productType");
		CommonModelAndView mv = new CommonModelAndView();
		List<ProductTypeDto> productType = productService.getProductType();
		model.addAttribute("producttype", productType);

		return mv;
	}

}
