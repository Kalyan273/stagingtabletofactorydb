package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.Role;
import com.factory.appraisal.factoryService.dto.RoleDropDowns;

public interface RoleService {
    /**
     * This method creates the role
     * @return message
     */
    Response addRole(Role role);

    /**
     * This method is used to update role of user
     * @param role
     * @param roleId
     * @return
     */
    Response updateRole(Role role,Long roleId) throws GlobalException;

    /**
     * This method is used to delete role of the user
     * @param roleId
     * @return
     */

    Response deleteRole(Long roleId) throws GlobalException;

    RoleDropDowns getRoleList();
}
