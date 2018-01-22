package com.hung.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hung.common.constants.CommonConstants;
import com.hung.common.utils.CommonArraysUtils;
import com.hung.common.utils.CommonStringUtils;

@Component
public class CommonMessageSource extends ReloadableResourceBundleMessageSource {

    /** Logger(不要な場合は削除). */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMessageSource.class);

    private static final String LOG_MESSAGE_NOT_FOUND = "key:'{0}' is not found.";
    /** 拡張子. */
    private static final String CONST_PROPERTIES = ".properties";

    /** Wildcard Resolver. */
    private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    /**
     * メッセージを取得する.
     *
     * @param key メッセージキー
     * @return メッセージ(該当なしの場合はデフォルトは空文字)
     */
    public String getMessage(String key) {
        try {
            return super.getMessage(key, new String[] {}, CommonConstants.EMPTY, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            LOGGER.error(CommonStringUtils.format(LOG_MESSAGE_NOT_FOUND, key));
            return CommonConstants.EMPTY;
        }
    }

    /**
     * 
     * @see org.springframework.context.support.AbstractResourceBasedMessageSource#setBasenames(java.lang.String[])
     * @param basenames
     */
    @Override
    public void setBasenames(String... basenames) {
        if (CommonArraysUtils.isNullOrEmpty(basenames)) {
            return;
        }

        List<String> baseNames = new ArrayList<>();
        for (String basename : basenames) {
            Assert.hasText(basename, "Basename must not be empty");
            try {
                Resource[] resources = resourcePatternResolver.getResources(basename.trim());
                for (Resource resource : resources) {
                    String uri = resource.getURI().toString();
                    String baseName = null;

                    if (resource instanceof FileSystemResource) {
                        baseName = "classpath:" + CommonStringUtils.substringBetween(uri, "/classes", CONST_PROPERTIES);
                    } else if (resource instanceof ClassPathResource) {
                        baseName = CommonStringUtils.substringBefore(uri, CONST_PROPERTIES);
                    }

                    // TODO
                    baseNames = trimLocale(baseName, baseNames);
                }
            } catch (IOException e) {
                LOGGER.debug("No message source files found for basename " + basename + ".");
            }
        }
        super.setBasenames(baseNames.toArray(new String[baseNames.size()]));
    }

    /**
     * Localeをトリムする.
     *
     * @param baseName トリム対象BaseName
     * @param baseNames 格納するBaseNameリスト
     * @return 追加されたBaseNameリスト
     */
    private List<String> trimLocale(String baseName, List<String> baseNames) {
        Locale[] allLocales = Locale.getAvailableLocales();
        int length = baseName.length();
        for (Locale locale : allLocales) {

            if (CommonStringUtils.isNullOrEmpty(locale.getLanguage())) {
                continue;
            }

            String trimedBaseName = CommonStringUtils.substringBefore(baseName, "_" + locale.getLanguage());
            if (length != trimedBaseName.length() && !baseNames.contains(trimedBaseName)) {
                baseNames.add(trimedBaseName);
                break;
            }
        }

        return baseNames;
    }
}
