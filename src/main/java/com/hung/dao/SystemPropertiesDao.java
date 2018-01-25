package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hung.dto.SystemPropertiesDto;

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
@Repository
@Transactional
public class SystemPropertiesDao extends JdbcDaoSupport implements ISystemPropertiesDao {

    @Autowired
    public SystemPropertiesDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<com.hung.dto.SystemPropertiesDto> getSystemProperties() {
        String sql = "Select * from SYS_PROPERTIES";
        List<SystemPropertiesDto> sysProperties = this.getJdbcTemplate().query(sql,
                new BeanPropertyRowMapper(SystemPropertiesDto.class));
        return sysProperties;
    }

    @Override
    public com.hung.dto.SystemPropertiesDto getSystemPropertie(String systemName) {
        String sql = "Select * from SYS_PROPERTIES where SYS_NAME = ? ";
        Object[] params = new Object[] {systemName};
        SystemPropertiesDto systemPropertiesDto = this.getJdbcTemplate().queryForObject(sql, params,
                SystemPropertiesDto.class);
        return systemPropertiesDto;
    }

}
