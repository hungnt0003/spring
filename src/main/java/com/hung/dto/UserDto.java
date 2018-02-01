package com.hung.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hung.common.AbstractDto;

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
public class UserDto extends AbstractDto {

    private String userName;
    private String password;
    private String passwordConfirm;
    private boolean enabled = true;
    private MultipartFile imgFile;
    private String avartaPath;
    private String thumbPath;
    private String firstName;
    private String lastName;
    private Date birth;
    private Date stDate;
    private Date edDate;
    private List<RoleDto> roles;
    private String role;
    private boolean rememberMe = false;
    private String email;
    private String sex;

    public UserDto() {

    }

    public UserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
     * passwordを取得する.
     * 
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * passwordを設定する.
     * 
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * enabledを取得する.
     * 
     * @return String enabled
     */
    public String getEnabled() {
        return enabled ? "1" : "0";
    }

    /**
     * enabledを設定する.
     * 
     * @param enabled enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * imgFileを取得する.
     * 
     * @return MultipartFile imgFile
     */
    public MultipartFile getImgFile() {
        return imgFile;
    }

    /**
     * imgFileを設定する.
     * 
     * @param imgFile imgFile
     */
    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    /**
     * avartaPathを取得する.
     * 
     * @return String avartaPath
     */
    public String getAvartaPath() {
        return avartaPath;
    }

    /**
     * avartaPathを設定する.
     * 
     * @param avartaPath avartaPath
     */
    public void setAvartaPath(String avartaPath) {
        this.avartaPath = avartaPath;
    }

    /**
     * thumbPathを取得する.
     * 
     * @return String thumbPath
     */
    public String getThumbPath() {
        return thumbPath;
    }

    /**
     * thumbPathを設定する.
     * 
     * @param thumbPath thumbPath
     */
    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    /**
     * firstNameを取得する.
     * 
     * @return String firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * firstNameを設定する.
     * 
     * @param firstName firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * lastNameを取得する.
     * 
     * @return String lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * lastNameを設定する.
     * 
     * @param lastName lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * birthを取得する.
     * 
     * @return Date birth
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * birthを設定する.
     * 
     * @param birth birth
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * stDateを取得する.
     * 
     * @return Date stDate
     */
    public Date getStDate() {
        return stDate;
    }

    /**
     * stDateを設定する.
     * 
     * @param stDate stDate
     */
    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    /**
     * edDateを取得する.
     * 
     * @return Date edDate
     */
    public Date getEdDate() {
        return edDate;
    }

    /**
     * edDateを設定する.
     * 
     * @param edDate edDate
     */
    public void setEdDate(Date edDate) {
        this.edDate = edDate;
    }

    /**
     * rolesを取得する.
     * 
     * @return List<RoleDto> roles
     */
    public List<RoleDto> getRoles() {
        return roles;
    }

    /**
     * rolesを設定する.
     * 
     * @param roles roles
     */
    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    /**
     * roleを取得する.
     * 
     * @return String role
     */
    public String getRole() {
        return role;
    }

    /**
     * roleを設定する.
     * 
     * @param role role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * rememberMeを取得する.
     * 
     * @return boolean rememberMe
     */
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * rememberMeを設定する.
     * 
     * @param rememberMe rememberMe
     */
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     * passwordConfirmを取得する.
     * 
     * @return String passwordConfirm
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * passwordConfirmを設定する.
     * 
     * @param passwordConfirm passwordConfirm
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * emailを取得する.
     * 
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * emailを設定する.
     * 
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * sexを取得する.
     * 
     * @return String sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * sexを設定する.
     * 
     * @param sex sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

}
