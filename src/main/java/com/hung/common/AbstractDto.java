package com.hung.common;

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
public class AbstractDto {

    private boolean newFlag;

    /**
     * newFlagを取得する.
     * 
     * @return boolean newFlag
     */
    public boolean isNewFlag() {
        return newFlag;
    }

    /**
     * newFlagを設定する.
     * 
     * @param newFlag newFlag
     */
    public void setNewFlag(boolean newFlag) {
        this.newFlag = newFlag;
    }
}
