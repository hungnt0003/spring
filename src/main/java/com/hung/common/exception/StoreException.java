package com.hung.common.exception;

public class StoreException extends Exception {

    /**
     * コンストラクタ.
     *
     * <pre>
     * 詳細メッセージ無しで新規作成する
     * </pre>
     */
    public StoreException() {
        super();
    }

    /**
     * コンストラクタ.
     *
     * <pre>
     * 指定された詳細メッセージを使用して新規作成する
     * </pre>
     *
     * @param message メッセージ
     */
    public StoreException(String message) {
        super(message);
    }

    /**
     * コンストラクタ.
     *
     * <pre>
     * 指定された理由を使用して新規作成する
     * </pre>
     *
     * @param cause 理由
     */

    public StoreException(Throwable cause) {
        super(cause);
    }

    /**
     * コンストラクタ.
     *
     * <pre>
     * 指定された詳細メッセージ、理由を使用して新規作成する
     * </pre>
     *
     * @param message メッセージ
     * @param cause 理由
     */
    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
