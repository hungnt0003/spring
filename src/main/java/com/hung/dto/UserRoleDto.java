package com.hung.dto;

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
public class UserRoleDto {

    private String roleId;
    private String userName;
    private String roleName;

    /**
     * roleIdを取得する.
     * 
     * @return String roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * roleIdを設定する.
     * 
     * @param roleId roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * userNameを取得する.
     * 
     * @return String userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * userNameを設定する.
     * 
     * @param userName userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * roleNameを取得する.
     * 
     * @return String roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * roleNameを設定する.
     * 
     * @param roleName roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
