package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hung.dao.mapper.RoleMapper;
import com.hung.dto.RoleDto;

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
@Repository
@Transactional
public class RoleDao extends JdbcDaoSupport implements IRoleDao {

    @Autowired
    public RoleDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * @see com.hung.dao.IRoleDao#getRoles()
     * @return
     */
    @Override
    public List<RoleDto> getRoles() {
        String sql = "SELECT * FROM ROLES";
        Object[] params = new Object[] {};
        RoleMapper mapper = new RoleMapper();
        List<RoleDto> roles = this.getJdbcTemplate().query(sql, params, mapper);
        return roles;
    }

    @Override
    public List<RoleDto> getRoles(String userName) {
        String sql = "SELECT * FROM USER_ROLES WHERE USERNAME=? ";
        Object[] params = new Object[] {userName};
        RoleMapper mapper = new RoleMapper();
        List<RoleDto> roles = this.getJdbcTemplate().query(sql, params, mapper);
        return roles;
    }

}
