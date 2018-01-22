package com.hung.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hung.common.CommonModelAndView;

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
public class ErrorController {

    /** ページなし例外(404) HTMLパス. */
    public static final String PAGE_NOT_FOUND_VIEW = "error/error_pageNotFound";
    /** 汎用例外(500) HTMLパス. */
    public static final String SYSTEM_ERROR_VIEW = "error/error_system";
    /** アップロード例外 HTMLパス. */
    public static final String UPLOAD_ERROR_VIEW = "error/error_upload";

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(Model model, HttpServletRequest httpRequest) {

        CommonModelAndView errorPage = new CommonModelAndView();
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
        case 400: {
            errorMsg = "Http Error Code: 400. Bad Request";
            break;
        }
        case 401: {
            errorMsg = "Http Error Code: 401. Unauthorized";
            break;
        }
        case 404: {
            errorMsg = "Http Error Code: 404. Resource not found";
            break;
        }
        case 500: {
            errorMsg = "Http Error Code: 500. Internal Server Error";
            break;
        }
        }
        model.addAttribute("mainContent", "screens/errors/error");
        model.addAttribute("loaderWrapper", "common/loader_wrapper");
        model.addAttribute("errorMsg", errorMsg);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
