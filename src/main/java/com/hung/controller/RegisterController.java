package com.hung.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hung.common.CommonController;
import com.hung.common.CommonModelAndView;
import com.hung.common.constants.UrlConstants;
import com.hung.common.utils.CommonStringUtils;
import com.hung.dto.UserDto;
import com.hung.service.IRegisterService;
import com.hung.validation.RegisterValidator;

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
public class RegisterController extends CommonController {

    /** IRegisterService. */
    @Autowired
    IRegisterService registerService;
    /** ポストValidator. */
    @Autowired
    private RegisterValidator registerValidator;

    @RequestMapping(value = UrlConstants.URL_REGISTER, method = RequestMethod.GET)
    public CommonModelAndView register(Model model, @ModelAttribute("userName") String userName,
            @ModelAttribute("email") String email) {

        if (CommonStringUtils.isNotNullOrEmpty(userName) || CommonStringUtils.isNotNullOrEmpty(email)) {
            UserDto userDto = new UserDto();
            userDto.setUserName(userName);
            userDto.setEmail(email);
            model.addAttribute(ELEMENT_KEY, userDto);
        }

        model.addAttribute("mainContent", "screens/register/register");

        return new CommonModelAndView();
    }

    @RequestMapping(value = "/" + UrlConstants.URL_REGISTER_STORE, method = RequestMethod.POST)
    public ModelAndView store(HttpServletRequest request, Model model, final RedirectAttributes redirectAttributes,
            @ModelAttribute(ELEMENT_KEY) @Validated UserDto userDto, BindingResult bindingResult) {

        CommonModelAndView mv = new CommonModelAndView();

        registerValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_RESULT + ELEMENT_KEY, bindingResult);
            redirectAttributes.addFlashAttribute(ELEMENT_KEY, userDto);

            if (userDto.isNewFlag()) {
                mv.setViewName(REDIRECT + UrlConstants.URL_REGISTER);
            } else {
                mv.setViewName(REDIRECT + UrlConstants.URL_REGISTER);
            }
            return mv;
        }

        try {
            registerService.storeUser(userDto);
        } catch (Exception e) {
            // TODO: handle exception
        }
        model.addAttribute("mainContent", "screens/register/register");
        model.addAttribute(ELEMENT_KEY, userDto);
        return new CommonModelAndView();
    }
}
