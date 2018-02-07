package com.hung.inf;

import java.util.List;

import com.hung.dto.UserRoleDto;

/**
 * 
 * @author Hung
 *
 */
public interface IUserRoleInterface {

    List<UserRoleDto> getUserRoles();

    List<UserRoleDto> getUserRoles(String userName);

    UserRoleDto getMaxRole(String userName);

    List<UserRoleDto> getUserRolesByRole(String role);

    UserRoleDto getUserRole(UserRoleDto userRoleDto);

    void storeUserRole(UserRoleDto userRoleDto);

    void deleteUserRole(String userName);
}
