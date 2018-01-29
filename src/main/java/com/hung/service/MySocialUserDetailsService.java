package com.hung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.hung.dto.social.MySocialUserDetails;

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
public class MySocialUserDetailsService implements SocialUserDetailsService {

    /** UserDetailsService. */
    @Autowired
    private UserDetailsService userDetailService;

    /**
     * Tải thông tin người dùng đăng nhập bởi mạng xã hội. (Phương thức này được sử dụng bởi Spring Social Security API)
     * 
     * @see org.springframework.social.security.SocialUserDetailsService#loadUserByUserId(java.lang.String)
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        // Dựa trên UserDetailService. (Xem MyDBAuthenticationService).
        UserDetails userDetails = userDetailService.loadUserByUsername(userId);
        return (MySocialUserDetails) userDetails;
    }

}
