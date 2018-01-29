package com.hung.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.CommonController;
import com.hung.common.CommonModelAndView;
import com.hung.common.constants.UrlConstants;
import com.hung.common.utils.CommonStringUtils;
import com.hung.dto.UserDto;
import com.hung.service.IUserService;

import javassist.NotFoundException;

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
public class UserController extends CommonController {

    /** IUserService. */
    @Autowired
    IUserService userService;

    private static final String SESSION_KEY_LIST = UserController.class.getSimpleName() + "LIST";

    @RequestMapping(value = "/" + UrlConstants.URL_ADMIN_USER, method = RequestMethod.GET)
    public ModelAndView posts(Model model) {

		// model.addAttribute("loaderWrapper", "common/loader_wrapper");
        model.addAttribute("mainContent", "screens/users/list");
        List<UserDto> userDtos = userService.getUsers();
        model.addAttribute(LIST_ELEMENT_KEY, userDtos);

        setSessionData(SESSION_KEY_LIST, userDtos);

        return new CommonModelAndView();

    }

    @RequestMapping(value = "/" + UrlConstants.URL_ADMIN_USER_EDIT, method = RequestMethod.GET)
    public ModelAndView postsEdit(Model model, @RequestParam(value = "userName", required = false) String userName) {

        CommonModelAndView mv = new CommonModelAndView();
        UserDto userDto = null;

        try {
            if (!model.containsAttribute(ELEMENT_KEY)) {
                if (CommonStringUtils.isNotNullOrEmpty(userName)) {
                    userDto = userService.getFullUser(userName);
                    userDto.setNewFlag(false);
                    model.addAttribute(ELEMENT_KEY, userDto);
                }
            }
        } catch (NotFoundException e1) {
            // TODO: handle exception
        } catch (Exception e2) {
            // TODO: handle exception
        }

        model.addAttribute("loaderWrapper", "common/loader_wrapper");
        model.addAttribute("mainContent", "screens/users/edit");

        return mv;

    }
}
