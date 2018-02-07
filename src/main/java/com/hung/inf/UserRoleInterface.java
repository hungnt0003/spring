/**
 * 
 */
package com.hung.inf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hung.common.utils.CommonObjectUtils;
import com.hung.dao.IUserRoleDao;
import com.hung.dto.UserRoleDto;

/**
 * @author Hung
 *
 */
@Component
public class UserRoleInterface implements IUserRoleInterface {

    @Autowired
    IUserRoleDao useRoleDao;

    /*
     * (non-Javadoc)
     * @see com.hung.inf.IUserRoleInterface#getUserRoles()
     */
    @Override
    public List<UserRoleDto> getUserRoles() {
        // TODO Auto-generated method stub
        return useRoleDao.getUserRoles();
    }

    /*
     * (non-Javadoc)
     * @see com.hung.inf.IUserRoleInterface#getUserRoles(java.lang.String)
     */
    @Override
    public List<UserRoleDto> getUserRoles(String userName) {
        // TODO Auto-generated method stub
        return useRoleDao.getUserRoles(userName);
    }

    /*
     * (non-Javadoc)
     * @see com.hung.inf.IUserRoleInterface#getMaxRole(java.lang.String)
     */
    @Override
    public UserRoleDto getMaxRole(String userName) {
        // TODO Auto-generated method stub
        return useRoleDao.getMaxRole(userName);
    }

    /*
     * (non-Javadoc)
     * @see com.hung.inf.IUserRoleInterface#getUserRolesByRole(java.lang.String)
     */
    @Override
    public List<UserRoleDto> getUserRolesByRole(String role) {
        // TODO Auto-generated method stub
        return useRoleDao.getUserRolesByRole(role);
    }

    /*
     * (non-Javadoc)
     * @see com.hung.inf.IUserRoleInterface#getUserRole(com.hung.dto.UserRoleDto)
     */
    @Override
    public UserRoleDto getUserRole(UserRoleDto userRoleDto) {
        // TODO Auto-generated method stub
        return useRoleDao.getUserRole(userRoleDto);
    }

    /*
     * (non-Javadoc)
     * @see com.hung.inf.IUserRoleInterface#storeUserRole(com.hung.dto.UserRoleDto)
     */
    @Override
    public void storeUserRole(UserRoleDto userRoleDto) {
        if (CommonObjectUtils.isNullOrEmpty(useRoleDao.getUserRole(userRoleDto))) {
            useRoleDao.addUserRule(userRoleDto);
        } else {
            useRoleDao.updateUserRule(userRoleDto);
        }
    }

    @Override
    public void deleteUserRole(String userName) {
        useRoleDao.deleteRuleDto(userName);
    }

}
