package com.hung.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.CommonController;
import com.hung.common.CommonModelAndView;
import com.hung.dto.ProductDTO;
import com.hung.dto.UserDto;
import com.hung.service.ILoginService;
import com.hung.service.IProductService;

/**
 *
 * [Controller] ログイン.
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Controller
public class LoginController extends CommonController {

    /** Logger(不要な場合は削除). */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    ILoginService loginService;

	@Autowired
	private IProductService productService;

    /**
     * ルートアクセス.
     *
     * @param locale Locale
     * @param loginDto ログインフォーム
     * @return URL(ログイン)
     */
    @RequestMapping(value = {"/", "welcome"}, method = RequestMethod.GET)
    public ModelAndView index(Model model, UserDto userDto) {
		// //return "forward:/" + "login";

		List<ProductDTO> productDto = productService.getProduct();
		model.addAttribute(LIST_ELEMENT_KEY, productDto);

		// model.addAttribute("mainContent", "screens/homePage/home");
		model.addAttribute("mainContent", "common/common_contentRight");

		return new CommonModelAndView();
    }

    /**
     * ログイン.
     *
     * @param loginDto ログイン情報
     * @param request Request
     * @param error RequestParameter : ログインエラー
     * @param logout RequestParameter : ログアウト
     * @param timeout RequestParameter : タイムアウト
     * @param lockOut RequestParameter : ロックアウト
     * @return ModelAndView(ログイン)
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public CommonModelAndView login(Model model, UserDto userDto,
            HttpServletRequest request,
            @RequestParam(value = "error", required = false) Optional<String> error,
            @RequestParam(value = "logout", required = false) Optional<String> logout,
            @RequestParam(value = "timeout", required = false) Optional<String> timeout,
            @RequestParam(value = "lockOut", required = false) Optional<String> lockOut) {

        if (logout.isPresent()) {
        } else if (error.isPresent()) {
            model.addAttribute("error", "Invalid username and password!");
        } else if (timeout.isPresent()) {
        } else if (lockOut.isPresent()) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        model.addAttribute("mainContent", "screens/login/login");
        return new CommonModelAndView();
    }

    /**
     * ログイン.
     *
     * @param loginDto ログイン情報
     * @param request Request
     * @param error RequestParameter : ログインエラー
     * @param logout RequestParameter : ログアウト
     * @param timeout RequestParameter : タイムアウト
     * @param lockOut RequestParameter : ロックアウト
     * @return ModelAndView(ログイン)
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public CommonModelAndView auth(UserDto userDto, HttpServletRequest request) {

        CommonModelAndView mv = new CommonModelAndView("screens/login/login");

        return mv;
    }

    /**
     * Check if user is login by remember me cookie, refer
     * org.springframework.security.authentication.AuthenticationTrustResolverImpl
     */
    private boolean isRememberMeAuthenticated() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }

    /**
     * save targetURL in session
     */
    private void setRememberMeTargetUrlToSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("targetUrl", "/admin/update");
        }
    }

    /**
     * get targetURL from session
     */
    private String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if (session != null) {
            targetUrl = session.getAttribute("targetUrl") == null ? ""
                    : session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
    }
}
