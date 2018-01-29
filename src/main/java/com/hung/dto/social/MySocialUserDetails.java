package com.hung.dto.social;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import com.hung.dto.RoleDto;
import com.hung.dto.UserDto;

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
public class MySocialUserDetails implements SocialUserDetails {

    private static final long serialVersionUID = -5246117266247684905L;

    private List<GrantedAuthority> auths = new ArrayList<>();
    private UserDto userDto;

    /**
     * authsを取得する.
     * 
     * @return List<GrantedAuthority> auths
     */
    public List<GrantedAuthority> getAuths() {
        return auths;
    }

    /**
     * authsを設定する.
     * 
     * @param auths auths
     */
    public void setAuths(List<GrantedAuthority> auths) {
        this.auths = auths;
    }

    /**
     * userDtoを取得する.
     * 
     * @return UserDto userDto
     */
    public UserDto getUserDto() {
        return userDto;
    }

    /**
     * userDtoを設定する.
     * 
     * @param userDto userDto
     */
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    /**
     * (デフォルト)コンストラクタ(ピリオド削除厳禁).
     * 
     * <pre>
     * 初期化内容, 使用例など(不要の場合は削除)
     * </pre>
     */
    public MySocialUserDetails(UserDto dto) {
        this.userDto = dto;
        List<RoleDto> roles = dto.getRoles();
        for (RoleDto roleDto : roles) {
            GrantedAuthority grant = new SimpleGrantedAuthority(roleDto.getName());
            getAuths().add(grant);
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuths();
    }

    @Override
    public String getPassword() {
        return getUserDto().getPassword();
    }

    @Override
    public String getUsername() {
        return getUserDto().getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUserId() {
        return getUserDto().getUserName();
    }

}
