package com.hung.dto;

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
public class SystemPropertiesDto {

    private String sysName;
    private String sysValue;

    /**
     * sysNameを取得する.
     * 
     * @return String sysName
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * sysNameを設定する.
     * 
     * @param sysName sysName
     */
    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    /**
     * sysValueを取得する.
     * 
     * @return String sysValue
     */
    public String getSysValue() {
        return sysValue;
    }

    /**
     * sysValueを設定する.
     * 
     * @param sysValue sysValue
     */
    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

}
