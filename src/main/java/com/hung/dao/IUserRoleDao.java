package com.hung.dao;

import java.util.List;

import com.hung.dto.UserRoleDto;

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
public interface IUserRoleDao {

    List<UserRoleDto> getUserRoles();

    List<UserRoleDto> getUserRoles(String userName);

    List<UserRoleDto> getUserRolesByRole(String role);

    UserRoleDto getUserRole(UserRoleDto userRoleDto);

    int addRuleDto(UserRoleDto userRoleDto);

    int updateRuleDto(UserRoleDto userRoleDto);

    int deleteRuleDto(UserRoleDto userRoleDto);
}
