package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hung.common.SqlQueryDefineIf;
import com.hung.dao.mapper.UserMapper;
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
@Service
@Transactional
public class UserInfoDao extends JdbcDaoSupport implements IUserInfoDao {

    @Autowired
    public UserInfoDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

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

    @Override
    public List<String> getUserRoles(String userName) {
        String sql = "Select r.User_Role "//
                + " from User_Roles r where r.Username = ? ";
        Object[] params = new Object[] {userName};

        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }

    @Override
    public List<UserDto> getUsers() {

        String sql = "Select * from Users";
        List<UserDto> users = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(UserDto.class));

        return users;

    }

    @Override
    public UserDto getFullUser(String userName) {
        String sql = "select * from USERS u "
                + "INNER JOIN USER_ROLES ur on u.USERNAME = ur.USERNAME "
                + "where u.USERNAME = ? ";

        Object[] params = new Object[] {userName};
        UserDto user = this.getJdbcTemplate().queryForObject(sql, params, UserDto.class);
        return user;
    }

    @Override
    public int addUser(UserDto userDto) {
        String sql = SqlQueryDefineIf.USER_ADD;
        return this.getJdbcTemplate().update(sql, new Object[] {userDto.getUserName(),
                userDto.getPassword(), userDto.getImg(), userDto.getFirstName(), userDto.getLastName()});
    }

    @Override
    public int edit(UserDto userDto) {
        String sql = SqlQueryDefineIf.USER_UPDATE;
        return this.getJdbcTemplate().update(sql, new Object[] {userDto.getPassword(), userDto.getEnabled(),
                userDto.getImg(), userDto.getFirstName(), userDto.getLastName(), userDto.getUserName(),
                userDto.getStDate()});
    }

    @Override
    public int delete(UserDto userDto) {
        String sql = SqlQueryDefineIf.USER_DELETE;
        return this.getJdbcTemplate().update(sql, new Object[] {userDto.getUserName(), userDto.getStDate()});
    }

}
