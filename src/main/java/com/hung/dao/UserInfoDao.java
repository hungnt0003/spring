package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hung.common.SqlQueryDefineIf;
import com.hung.dao.mapper.UserMapper;
import com.hung.dto.UserDto;

/**
 * Class progess tranfer user info with db.
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Repository
@Transactional
public class UserInfoDao extends JdbcDaoSupport implements IUserInfoDao {

    /**
     * 
     * Init dataSource.
     * 
     * @param dataSource dataSource
     */
    @Autowired
    public UserInfoDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * 
     * @see com.hung.dao.IUserInfoDao#getUser(java.lang.String)
     * @param userName
     * @return
     */
    @Override
    public UserDto getUser(String userName) {
        String sql = "Select u.Username,u.Password "//
                + " from Users u where u.Username = ? ";
        Object[] params = new Object[] {userName};
        UserMapper mapper = new UserMapper();
        try {
            UserDto user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 
     * @see com.hung.dao.IUserInfoDao#getUserRoles(java.lang.String)
     * @param userName
     * @return
     */
    @Override
    public List<String> getUserRoles(String userName) {
        String sql = "Select r.User_Role "//
                + " from User_Roles r where r.Username = ? ";
        Object[] params = new Object[] {userName};

        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }

    /**
     * 
     * @see com.hung.dao.IUserInfoDao#getUsers()
     * @return
     */
    @Override
    public List<UserDto> getUsers() {

        String sql = "Select * from Users";
        List<UserDto> users = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(UserDto.class));

        return users;

    }

    /**
     * 
     * @see com.hung.dao.IUserInfoDao#getFullUser(java.lang.String)
     * @param userName
     * @return
     */
    @Override
    public UserDto getFullUser(String userName) {
        String sql = "select * from USERS u "
                + "INNER JOIN USER_ROLES ur on u.USERNAME = ur.USERNAME "
                + "where u.USERNAME = ? ";

        Object[] params = new Object[] {userName};
        UserDto user = this.getJdbcTemplate().queryForObject(sql, params, UserDto.class);
        return user;
    }

    /**
     * 
     * @see com.hung.dao.IUserInfoDao#addUser(com.hung.dto.UserDto)
     * @param userDto
     * @return
     */
    @Override
    public int addUser(UserDto userDto) {
        String sql = SqlQueryDefineIf.USER_ADD;
        return this.getJdbcTemplate().update(sql, new Object[] {userDto.getUserName(),
                userDto.getPassword(), userDto.getAvartaPath(), userDto.getFirstName(), userDto.getLastName(),
                userDto.getSex(), userDto.getThumbPath()});
    }

    /**
     * 
     * @see com.hung.dao.IUserInfoDao#edit(com.hung.dto.UserDto)
     * @param userDto
     * @return
     */
    @Override
    public int edit(UserDto userDto) {
        String sql = SqlQueryDefineIf.USER_UPDATE;
        return this.getJdbcTemplate().update(sql, new Object[] {userDto.getPassword(), userDto.getEnabled(),
                userDto.getAvartaPath(), userDto.getFirstName(), userDto.getLastName(), userDto.getSex(),
                userDto.getThumbPath(), userDto.getUserName()});
    }

    /**
     * 
     * @see com.hung.dao.IUserInfoDao#delete(com.hung.dto.UserDto)
     * @param userDto
     * @return
     */
    @Override
    public int delete(UserDto userDto) {
        String sql = SqlQueryDefineIf.USER_DELETE;
        return this.getJdbcTemplate().update(sql, new Object[] {userDto.getUserName(), userDto.getStDate()});
    }

}
