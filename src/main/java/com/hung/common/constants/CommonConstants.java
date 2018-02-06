package com.hung.common.constants;

import java.util.ArrayList;
import java.util.List;

public final class CommonConstants {

    /** 空文字. */
    public static final String EMPTY = "";
    /** プロパティファイルキー：システム文字コード. */
    public static final String APPLICATION_KEY_ENCODING = "system.character.encoding";

    public static final int MAX_USERNAME = 36;

    /** MAX_SIZE_UPLOAD 5MB. */
    public static final int MAX_SIZE_UPLOAD = 5 * 1024 * 1024;

    public static final String MAN = "MAN";
    public static final String WOMAN = "WOMAN";

    /** 代行入力. **/
    public static final List<String> WORKFLOW_PROXYS = (new ArrayList<String>() {

        {
            add("1");
            add("入力代行");
            add("2");
            add("承認権限移譲");
        }
    });
}
