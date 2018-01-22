package com.hung.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CommonController {

    /** Logger(不要な場合は削除). */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    /** ModelKey : 検索結果. */
    protected static final String LIST_ELEMENT_KEY = "listElement";
    /** ModelKey : 詳細結果. */
    protected static final String ELEMENT_KEY = "element";

    /** BindingResultクラス. */
    protected static final String BINDING_RESULT = "org.springframework.validation.BindingResult.";
    /** URL - リダイレクト. */
    protected static final String REDIRECT = "redirect:/";

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
}
