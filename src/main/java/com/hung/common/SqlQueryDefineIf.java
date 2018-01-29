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
    
    public static final String PRODUCT_ADD = "INSERT INTO PRODUCT(ID_PRODUCT, NAME, PRICE, ST_DATE, ID_TYPE, STATUS, IMAGE, COLOR, SALE, BRAND, ORIGIN, MADE_IN, SIZE, U_TIMESTAMP, ID_POST)"
    		+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";
    
    public static final String PRODUCT_UPDATE = "UPDATE PRODUCT"
    		+ "set"
    		+ "PRICE = ?"
    		+ "ST_DATE = ?"
    		+ "ID_TYPE = ?"
    		+ "STATUS = ?"
    		+ "IMAGE = ?"
    		+ "COLOR = ?"
    		+ "SALE = ?"
    		+ "BRAND = ?"
    		+ "ORIGIN = ?"
    		+ "MADE_IN = ?"
    		+ "SIZE = ?"
    		+ "NAME = ?"
    		+ "WHERE"
    		+ "ID_PRODUCT = ?"; 
    
    public static final String PRODUCT_DELETE = "delete from PRODUCT "
    		+ "WHERE"
    		+ "ID_PRODUCT = ?";
    
}
