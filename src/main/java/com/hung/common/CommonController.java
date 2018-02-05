package com.hung.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hung.common.utils.CommonStringUtils;

public abstract class CommonController {

    /** MessageResource. */
    @Autowired
    protected CommonMessageSource messageSource;

    /** Logger(不要な場合は削除). */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    /** ModelKey : 検索結果. */
    protected static final String LIST_ELEMENT_KEY = "listElement";
    /** ModelKey : 詳細結果. */
    protected static final String ELEMENT_KEY = "element";
    protected static final String MAIN_CONTENT_VIEW_KEY = "mainContent";
    /** ModelKey : . */
    protected static final String COMMON_ERR_MESS = "commonErrMess";

    /** BindingResultクラス. */
    protected static final String BINDING_RESULT = "org.springframework.validation.BindingResult.";
    /** URL - リダイレクト. */
    protected static final String REDIRECT = "redirect:/";

    /** SESSION_USER_LOGIN. */
    protected static final String SESSION_USER_LOGIN = "userLoginSession";

    /**
     * セッション情報を取得する.
     *
     * @param sessionKey セッションキー
     * @return セッション情報
     */
    protected Object getSessionData(String sessionKey) {
        return CommonSessionUtils.getSessionData(sessionKey);
    }

    /**
     * セッション情報を設定する.
     *
     * @param sessionKey セッションキー
     * @param sessionData セッション情報
     */
    protected void setSessionData(String sessionKey, Object sessionData) {
        CommonSessionUtils.setSessionData(sessionKey, sessionData);

    }

    /**
     * セッション情報を削除する.
     *
     * @param sessionKey 削除対象セッションキー
     */
    protected void clearSessionData(String sessionKey) {
        CommonSessionUtils.clearSessionData(sessionKey);
    }

    /**
     * セッション情報を削除する.
     *
     * @param sessionKeys 削除対象セッションキーリスト
     */
    protected void clearSessionData(String... sessionKeys) {
        CommonSessionUtils.clearSessionData(sessionKeys);
    }

    /**
     *
     * UnExpectedExceptionハンドルの処理.
     * <p>
     * 、Messageを設定して、UrlがあればすぐModelAndViewを返す
     * </p>
     *
     * @param redirectAttributes RedirectAttributes
     * @param url Url
     * @return T3ModelAndView
     */
    protected ModelAndView handleException(RedirectAttributes redirectAttributes, String url, String messKey) {
        setErrorMessage(redirectAttributes, messKey);
        return getReturnViewFromUrl(url);
    }

    /**
     * 予期せぬエラーが発生しましたメッセージを設定する.
     *
     * @param model Model
     */
    protected void setErrorMessage(Model model, String mess) {
        String message = messageSource.getMessage(mess);
        model.addAttribute(COMMON_ERR_MESS, CommonStringUtils.toEmptyIfNull(message));
    }

    /**
     *
     * ModelAndViewを返す.
     * <p>
     * UrlがあればModelAndViewを返す
     * </p>
     *
     * @param urls Url
     * @return T3ModelAndView
     */
    private ModelAndView getReturnViewFromUrl(String url) {
        if (CommonStringUtils.isNotNullOrEmpty(url)) {
            return new ModelAndView(url);
        }
        return null;
    }

    public String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
