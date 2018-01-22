package com.hung.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.hung.common.CommonMessageSource;
import com.hung.common.constants.CommonConstants;

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
@EnableWebMvc
@Configuration
@PropertySource("classpath:setting/web/web.properties")
public class WebMessageConfig {

    /** Environment. */
    @Resource
    private Environment env;

    /** Views - デフォルト. */
    private static final String KEY_VIEWS_DEFAULT = "message.web.views.basename.default";
    /** Validations - デフォルト. */
    private static final String KEY_VALIDATIONS_DEFAULT = "message.web.validations.basename.default";

    /**
     * メッセージソース・ビーンの作成.
     *
     * <pre>
     * WEBページでプロパティファイルを使用できる
     * 日本語メッセージ：messages_ja.properties.
     * </pre>
     *
     * @return MessageSource
     */
    @Bean
    public CommonMessageSource messageSource() {
        CommonMessageSource messageSource = new CommonMessageSource();
        // 設定は先勝
        messageSource.setBasenames(
                // デフォルト
                env.getProperty(KEY_VIEWS_DEFAULT),
                env.getProperty(KEY_VALIDATIONS_DEFAULT));
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding(env.getProperty(CommonConstants.APPLICATION_KEY_ENCODING));
        messageSource.setCacheSeconds(0); // TODO 0はだめ
        return messageSource;
    }
}
