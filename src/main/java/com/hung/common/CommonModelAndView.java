package com.hung.common;

import org.springframework.web.servlet.ModelAndView;

public class CommonModelAndView extends ModelAndView {

    /** テンプレートHtmlビュー. */
    private static final String APPLICATION_VIEW_TEMPLATE = "template/template";

    /**
     * デフォルトコンストラクタ.
     */
    public CommonModelAndView() {
        super(APPLICATION_VIEW_TEMPLATE);
    }

    /**
     * コンストラクタ.
     *
     * @param view テンプレートHTML名, URLなど
     */
    public CommonModelAndView(String view) {
        super(view);
    }

}
