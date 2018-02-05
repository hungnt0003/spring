package com.hung.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.AdminModelAndView;
import com.hung.common.CommonController;
import com.hung.common.CommonModelAndView;
import com.hung.common.constants.UrlConstants;
import com.hung.common.utils.CommonObjectUtils;
import com.hung.dto.UserDto;
import com.hung.inf.IUserInterface;

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
public class AdminController extends CommonController {

    /** UserInterface. */
    @Autowired
    IUserInterface userInterface;

    @RequestMapping("/admin")
    public ModelAndView hello(Model model) {

        UserDto dto = userInterface.getUser(getUserName());
        if (CommonObjectUtils.isNullOrEmpty(dto)) {
            // TODO
        }

        setSessionData(SESSION_USER_LOGIN, dto);

        model.addAttribute("mainContent", "screens/admin/admin2");
        return new AdminModelAndView();
    }

    @RequestMapping(value = "/" + UrlConstants.URL_ADMIN_POST, method = RequestMethod.GET)
    public ModelAndView posts(Model model) {

        model.addAttribute("loaderWrapper", "common/loader_wrapper");
        model.addAttribute("mainContent", "screens/admin/admin");

        return new CommonModelAndView();

    }
}
