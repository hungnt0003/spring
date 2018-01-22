package com.hung.common;

import org.springframework.web.servlet.ModelAndView;

public class CommonBaseModelAndView extends ModelAndView {

    /** テンプレートHtmlビュー. */
    private static final String APPLICATION_VIEW_TEMPLATE = "template/templateCategory";

    /**
     * デフォルトコンストラクタ.
     */
    public CommonBaseModelAndView() {
        super(APPLICATION_VIEW_TEMPLATE);
    }

    /**
     * コンストラクタ.
     *
     * @param view テンプレートHTML名, URLなど
     */
    public CommonBaseModelAndView(String view) {
        super(view);
    }

}
