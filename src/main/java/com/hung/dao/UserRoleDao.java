package com.hung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hung.dao.mapper.UserRoleMapper;
import com.hung.dto.UserRoleDto;

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
public class UserRoleDao extends JdbcDaoSupport implements IUserRoleDao {

	/**
	 * 
	 * Init dataSource.
	 * 
	 * @param dataSource
	 *            dataSource
	 */
	@Autowired
	public UserRoleDao(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	/**
	 * @see com.hung.dao.IUserRoleDao#getUserRoles()
	 * @return
	 */
	@Override
	public List<UserRoleDto> getUserRoles() {
		// String sql = "SELECT * FROM USER_ROLES";
		// UserMapper mapper = new UserMapper();
		// try {
		// UserDto user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		// return user;
		// } catch (EmptyResultDataAccessException e) {
		// return null;
		// }
		return null;
	}

	/**
	 * @see com.hung.dao.IUserRoleDao#getUserRoles(java.lang.String)
	 * @param userName
	 * @return
	 */
	@Override
	public List<UserRoleDto> getUserRoles(String userName) {
		String sql = "SELECT ur.USERNAME, r.ROLE_ID, r.ROLE_NAME FROM user_roles ur "
				+ "INNER JOIN roles r ON r.ROLE_ID = ur.USER_ROLE " + "WHERE ur.USERNAME = ? ";
		UserRoleMapper mapper = new UserRoleMapper();
		Object[] params = new Object[] { userName };
		try {
			List<UserRoleDto> userRoleDtoList = this.getJdbcTemplate().query(sql, params, mapper);
			return userRoleDtoList;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * @see com.hung.dao.IUserRoleDao#getUserRolesByRole(java.lang.String)
	 * @param role
	 * @return
	 */
	@Override
	public List<UserRoleDto> getUserRolesByRole(String role) {
		// TODO Auto-Generated Method Stub
		return null;
	}

	/**
	 * @see com.hung.dao.IUserRoleDao#getUserRole(com.hung.dto.UserRoleDto)
	 * @param userRoleDto
	 * @return
	 */
	@Override
	public UserRoleDto getUserRole(UserRoleDto userRoleDto) {
		// TODO Auto-Generated Method Stub
		return null;
	}

	/**
	 * @see com.hung.dao.IUserRoleDao#addRuleDto(com.hung.dto.UserRoleDto)
	 * @param userRoleDto
	 * @return
	 */
	@Override
	public int addRuleDto(UserRoleDto userRoleDto) {
		// TODO Auto-Generated Method Stub
		return 0;
	}

	/**
	 * @see com.hung.dao.IUserRoleDao#updateRuleDto(com.hung.dto.UserRoleDto)
	 * @param userRoleDto
	 * @return
	 */
	@Override
	public int updateRuleDto(UserRoleDto userRoleDto) {
		// TODO Auto-Generated Method Stub
		return 0;
	}

	/**
	 * @see com.hung.dao.IUserRoleDao#deleteRuleDto(com.hung.dto.UserRoleDto)
	 * @param userRoleDto
	 * @return
	 */
	@Override
	public int deleteRuleDto(UserRoleDto userRoleDto) {
		// TODO Auto-Generated Method Stub
		return 0;
	}

	@Override
	public UserRoleDto getMaxRole(String userName) {
		String sql = "SELECT UR.USERNAME, R.ROLE_ID, R.ROLE_NAME FROM USER_ROLES ur "
				+ "INNER JOIN ROLES r ON ur.USER_ROLE = r.ROLE_ID "
				+ "WHERE UR.USERNAME = ? AND UR.USER_ROLE = (SELECT  MAX(USER_ROLE) USER_ROLE FROM USER_ROLES WHERE USERNAME = ?)";
		UserRoleMapper mapper = new UserRoleMapper();
		Object[] params = new Object[] { userName, userName };
		try {
			UserRoleDto userRoleDto = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userRoleDto;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
