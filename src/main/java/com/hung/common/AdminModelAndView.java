package com.hung.common;

import org.springframework.web.servlet.ModelAndView;

public class AdminModelAndView extends ModelAndView {

    /** テンプレートHtmlビュー. */
    private static final String APPLICATION_VIEW_TEMPLATE = "template/templateAdmin";

    /**
     * デフォルトコンストラクタ.
     */
    public AdminModelAndView() {
        super(APPLICATION_VIEW_TEMPLATE);
    }

    /**
     * コンストラクタ.
     *
     * @param view テンプレートHTML名, URLなど
     */
    public AdminModelAndView(String view) {
        super(view);
    }

}
