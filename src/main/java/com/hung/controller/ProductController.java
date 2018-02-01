/**
 *
 */
package com.hung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.CommonController;
import com.hung.common.CommonModelAndView;
import com.hung.dto.ProductDto;
import com.hung.service.IProductService;

/**
 * @author WBC01
 *
 */
@Controller
public class ProductController extends CommonController {
	@Autowired
	private IProductService productService;

	private static final String SESSION_KEY_LIST = ProductController.class.getSimpleName() + "LIST";

	// @RequestMapping(value = { "/", "welcome" }, method = RequestMethod.GET)
	public ModelAndView allProducts(Model model) {
		// nodel.addAttribute()
		model.addAttribute("mainContent", "common/common_contentRight");
		List<ProductDto> productDto = productService.getProduct();
		model.addAttribute(LIST_ELEMENT_KEY, productDto);

		setSessionData(SESSION_KEY_LIST, productDto);
		return new CommonModelAndView();

	}

}
