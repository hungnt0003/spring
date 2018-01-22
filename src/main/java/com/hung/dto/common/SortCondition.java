package com.hung.dto.common;

import com.hung.common.utils.CommonStringUtils;

/**
 * ソート条件.
 *
 * <pre>
 * ソート条件を保持する
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
public class SortCondition {

    /** ソートキー. */
    private String key;
    /** 昇順・降順(True : 降順 / False : 昇順). */
    private boolean reverse;
    /** 比較対象を数値(Integer)として比較するかどうか. */
    private boolean compareInteger;

    /**
     * コンストラクタ.
     *
     * @param key ソートキー
     * @param reverse True : 降順 / False : 昇順
     */
    public SortCondition(String key, boolean reverse) {
        if (CommonStringUtils.isNullOrEmpty(key)) {
            // TODO INODAI
            throw new IllegalArgumentException();
        }
        this.key = key;
        this.reverse = reverse;
    }

    /**
     * コンストラクタ.
     *
     * <pre>
     * 数値(int, Integer)の場合は使用しないこと
     * </pre>
     *
     * @param key ソートキー
     * @param reverse True : 降順 / False : 昇順
     * @param compareInteger True : 比較対象を数値(Integer)として比較 / False : 左記以外
     */
    public SortCondition(String key, boolean reverse, boolean compareInteger) {
        this(key, reverse);
        this.compareInteger = compareInteger;
    }

    /**
     * ソートキーを取得する.
     *
     * @return ソートキー
     */
    public String getKey() {
        return this.key;
    }

    /**
     * 昇順・降順を取得する.
     *
     * @return 昇順・降順
     */
    public boolean isReverse() {
        return this.reverse;
    }

    /**
     * 比較対象を数値(Integer)として比較するかどうかを取得する.
     *
     * @return True: 比較対象を数値(Integer)として比較 / False : 左記以外
     */
    public boolean isCompareInteger() {
        return this.compareInteger;
    }

}
