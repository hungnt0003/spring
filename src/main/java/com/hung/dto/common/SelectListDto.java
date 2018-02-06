package com.hung.dto.common;

/**
 * 
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
public class SelectListDto {

    private String key;
    private String value;

    /**
     * (デフォルト)コンストラクタ(ピリオド削除厳禁).
     * 
     * <pre>
     * 初期化内容, 使用例など(不要の場合は削除)
     * </pre>
     * 
     * @param key
     * @param value
     */
    public SelectListDto(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    /**
     * keyを取得する.
     * 
     * @return String key
     */
    public String getKey() {
        return key;
    }

    /**
     * keyを設定する.
     * 
     * @param key key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * valueを取得する.
     * 
     * @return String value
     */
    public String getValue() {
        return value;
    }

    /**
     * valueを設定する.
     * 
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
