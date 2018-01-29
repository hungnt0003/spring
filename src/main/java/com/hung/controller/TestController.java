package com.hung.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.CommonModelAndView;


// test thanh
/**
 * 
 * TestController.
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    // Action
    @RequestMapping("/hello")
    public ModelAndView hello(Model model) {
        logger.debug("Requesting to /hello");

        model.addAttribute("mainContent", "screens/homePage/home");

        logger.debug("/home returns to the hello");
        return new CommonModelAndView();

    }

    @RequestMapping("/staticResource")
    public String staticResource(Model model) {

        return "staticResource";

    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
