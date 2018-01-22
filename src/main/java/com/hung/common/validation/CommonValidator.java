package com.hung.common.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hung.common.CommonMessageSource;
import com.hung.common.constants.ValidationConstants;
import com.hung.common.utils.CommonObjectUtils;
import com.hung.common.utils.CommonStringUtils;

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
@Component
public abstract class CommonValidator implements Validator {

    /** MessageResource. */
    @Autowired
    protected CommonMessageSource messageSource;

    protected static final String DEFAULT_MESSAGE = "Default Message";

    /**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     * @param target チェック対象
     * @param errors エラー情報
     */
    @Override
    public void validate(Object target, Errors errors) {
        simpleCheck(target, errors);
        if (errors.hasErrors()) {
            return;
        }
        relationCheck(target, errors);
    }

    /**
     * 単項目チェック.
     *
     * @param target チェック対象
     * @param errors エラー情報
     */
    protected abstract void simpleCheck(Object target, Errors errors);

    /**
     * 相関項目チェック.
     *
     * @param target チェック対象
     * @param errors エラー情報
     */
    protected abstract void relationCheck(Object target, Errors errors);

    /**
     * 必須入力チェック.
     *
     * @param errors エラー情報
     * @param field チェック対象フィールド
     * @param key 単語キー
     * @param target チェック対象
     * @return True : 異常あり / False : 異常なし
     */
    protected boolean isRequiredInput(Errors errors, String field, String key, Object target) {
        boolean hasError = false;
        String[] words = getWords(key);
        if (CommonObjectUtils.isNullOrEmpty(target)) {
            errors.rejectValue(field, ValidationConstants.MSG_KEY_REQUIRED_INPUT_REP, words, DEFAULT_MESSAGE);
            hasError = true;
        }

        return hasError;
    }

    /**
     * 半角英数.
     *
     * <pre>
     * 処理内容, 使用例など(不要の場合は削除)
     * </pre>
     *
     * @param errors
     * @param field
     * @param key
     * @param target
     * @return
     */
    protected boolean isHalfAlpha(Errors errors, String field, String target) {
        boolean hasError = false;
        if (!CommonStringUtils.isDigitAlphabetCheck(target)) {
            errors.rejectValue(field, ValidationConstants.MSG_KEY_HALF_ALPHA_NUM, null);
            hasError = true;
        }
        return hasError;
    }

    /**
     * 固定長(int).
     *
     * <pre>
     * 処理内容, 使用例など(不要の場合は削除)
     * </pre>
     *
     * @param errors
     * @param field
     * @param key
     * @param target
     * @param length
     * @return
     */
    protected boolean isLength(Errors errors, String field, String key, String target, int length) {
        boolean hasError = false;
        String[] words = getWords(key);
        if (!CommonStringUtils.isStrByteLength(target, length)) {
            String[] resetWord = new String[] {words[0], String.valueOf(length)};
            errors.rejectValue(field, ValidationConstants.MSG_KEY_MOJI_LENGTH_REP, resetWord, DEFAULT_MESSAGE);
            hasError = true;
        }
        return hasError;
    }

    /**
     * 置換文字列(単語)を取得する.
     *
     * @param keys 置換文字列取得キー
     * @return 置換文字列
     */
    protected String[] getWords(String... keys) {
        String[] ret = new String[keys.length];
        for (int idx = 0; idx < keys.length; idx++) {
            ret[idx] = messageSource.getMessage(keys[idx]);
        }
        return ret;
    }
}
