package com.hung.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.CommonModelAndView;
import com.hung.common.constants.UrlConstants;

/**
 * クラスタイトル(ピリオド削除厳禁).
 *
 * <pre>
 * 内容, 使用例など
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public ModelAndView hello(Model model) {

        // model.addAttribute("loaderWrapper", "common/loader_wrapper");
        // model.addAttribute("mainContent", "screens/admin/admin");

        return new ModelAndView("screens/admin/admin2");

    }

    @RequestMapping(value = "/" + UrlConstants.URL_ADMIN_POST, method = RequestMethod.GET)
    public ModelAndView posts(Model model) {

        model.addAttribute("loaderWrapper", "common/loader_wrapper");
        model.addAttribute("mainContent", "screens/admin/admin");

        return new CommonModelAndView();

    }
}
