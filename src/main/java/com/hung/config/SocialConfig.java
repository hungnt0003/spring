package com.hung.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import com.hung.dao.ISystemPropertiesDao;
import com.hung.dao.IUserInfoDao;

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
@Configuration
@EnableSocial
// Load to Environment.
@PropertySource("classpath:setting/web/social-config.properties")
public class SocialConfig implements SocialConfigurer {

    /** SystemPropertiesDao. */
    @Autowired
    ISystemPropertiesDao systemPropertiesDao;
    /** DataSource. */
    @Autowired
    private DataSource dataSource;
    /** UserInfoDao. */
    @Autowired
    private IUserInfoDao userInfoDao;

    private boolean autoSignUp = systemPropertiesDao.getSystemPropertie("AUTO_SIGN_UP").equals("0") ? false : true;

    /**
     * 
     * @see org.springframework.social.config.annotation.SocialConfigurer#addConnectionFactories(org.springframework.social.config.annotation.ConnectionFactoryConfigurer,
     *      org.springframework.core.env.Environment)
     * @param arg0
     * @param arg1
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionConfig, Environment env) {
        // Twitter
        TwitterConnectionFactory tfactory = new TwitterConnectionFactory(//
                env.getProperty("twitter.consumer.key"), //
                env.getProperty("twitter.consumer.secret"));

        // tfactory.setScope(env.getProperty("twitter.scope"));

        connectionConfig.addConnectionFactory(tfactory);

        // Facebook
        FacebookConnectionFactory ffactory = new FacebookConnectionFactory(//
                env.getProperty("facebook.app.id"), //
                env.getProperty("facebook.app.secret"));

        ffactory.setScope(env.getProperty("facebook.scope"));

        // auth_type=reauthenticate

        connectionConfig.addConnectionFactory(ffactory);

        // Linkedin
        LinkedInConnectionFactory lfactory = new LinkedInConnectionFactory(//
                env.getProperty("linkedin.consumer.key"), //
                env.getProperty("linkedin.consumer.secret"));

        lfactory.setScope(env.getProperty("linkedin.scope"));

        connectionConfig.addConnectionFactory(lfactory);

        // Google
        GoogleConnectionFactory gfactory = new GoogleConnectionFactory(//
                env.getProperty("google.client.id"), //
                env.getProperty("google.client.secret"));

        gfactory.setScope(env.getProperty("google.scope"));

        connectionConfig.addConnectionFactory(gfactory);
    }

    /**
     * The UserIdSource determines the userID of the user.
     * 
     * @see org.springframework.social.config.annotation.SocialConfigurer#getUserIdSource()
     * @return UserIdSource
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    /**
     * 
     * @see org.springframework.social.config.annotation.SocialConfigurer#getUsersConnectionRepository(org.springframework.social.connect.ConnectionFactoryLocator)
     * @param arg0
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connection) {
        // org.springframework.social.security.SocialAuthenticationServiceRegistry
        JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
                connection,

                Encryptors.noOpText());

        if (autoSignUp) {
            // Cấu hình để:
            // Sau khi đăng nhập vào mạng xã hội.
            // Tự động tạo ra USER_ACCOUNT tương ứng nếu chưa có.
            ConnectionSignUp connectionSignUp = new MyConnectionSignUp(userInfoDao);
            usersConnectionRepository.setConnectionSignUp(connectionSignUp);
        } else {
            // Cấu hình để:
            // Sau khi đăng nhập vào mạng xã hội.
            // Nếu không tìm thấy bản ghi USER_ACCOUNT tương ứng
            // Chuyển tới trang đăng ký.
            usersConnectionRepository.setConnectionSignUp(null);
        }
        return usersConnectionRepository;
    }

    /**
     * 
     * This bean manages the connection flow between the account provider and the example application.
     * 
     * @param connectionFactoryLocator
     * @param connectionRepository
     * @return
     */
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, //
            ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
