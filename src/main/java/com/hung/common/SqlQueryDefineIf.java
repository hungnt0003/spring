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
public final class SqlQueryDefineIf {

    public static final String USER_ADD = "INSERT INTO USERS(USERNAME, PASSWORD, IMG, FIRSTNAME, LASTNAME) "
            + "VALUES (?, ?, ?, ?, ?)";

    public static final String USER_UPDATE = "update USERS "
            + "set "
            + "PASSWORD = ?, "
            + "ENABLED = ?, "
            + "IMG = ?, "
            + "FIRSTNAME = ? "
            + "LASTNAME = ? "
            + "U_TIMESTAMP = SYSDATE "
            + "where "
            + "USERNAME = ? "
            + "and STDATE = ? ";

    public static final String USER_DELETE = "delete from USERS "
            + "where "
            + "USERNAME = ? "
            + "and STDATE = ? ";
}
