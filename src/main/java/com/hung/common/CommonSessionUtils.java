package com.hung.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class CommonSessionUtils implements ICommonSesstionUtils {

    /** Logger(不要な場合は削除). */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonSessionUtils.class);

    /**
     * (デフォルト)コンストラクタ(ピリオド削除厳禁).
     * 
     * <pre>
     * 初期化内容, 使用例など(不要の場合は削除)
     * </pre>
     */
    public CommonSessionUtils() {
        // TODO Auto-Generated Constructor Stub
        super();
    }

    /**
     * セッション情報を取得する.
     *
     * @param sessionKey セッションキー
     * @return セッション情報
     */
    public static Object getSessionData(String sessionKey) {
        return getSession(sessionKey);
    }

    /**
     * セッション情報を取得する.
     *
     * @param sessionKey セッションキー
     * @param sessionData セッション情報
     */
    public static void setSessionData(String sessionKey, Object sessionData) {
        setSession(sessionKey, sessionData);
    }

    /**
     * セッション情報を削除する.
     *
     * @param sessionKeys 削除対象セッションキーリスト
     */
    public static void clearSessionData(String... sessionKeys) {
        for (String sessionKey : sessionKeys) {
            clearSession(sessionKey);
        }
    }

    /**
     * セッション情報を削除する.
     *
     * @param sessionKey 削除対象セッションキー
     */
    public static void clearSessionData(String sessionKey) {
        clearSession(sessionKey);
    }

    /**
     * セッション情報を取得する.
     *
     * @param sessionKey セッションキー
     * @return セッション情報
     */
    private static Object getSession(String sessionKey) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        return httpServletRequest.getSession(true).getAttribute(sessionKey);
    }

    /**
     * セッション情報を設定する.
     *
     * @param sessionKey セッションキー
     * @param sessionData セッション情報
     */
    private static void setSession(String sessionKey, Object sessionData) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        httpServletRequest.getSession(true).setAttribute(sessionKey, sessionData);
    }

    /**
     * セッション情報を削除する.
     *
     * @param sessionKey 削除対象セッションキー
     */
    private static void clearSession(String sessionKey) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        httpServletRequest.getSession(true).removeAttribute(sessionKey);
    }

    /**
     * HTTPリクエストを取得する.
     *
     * @return HTTPリクエスト
     */
    private static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
