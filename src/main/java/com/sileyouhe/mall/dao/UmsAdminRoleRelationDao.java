package com.sileyouhe.mall.dao;

import com.sileyouhe.mall.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAdminRoleRelationDao {
    /**
     * getPermissionList by admin ID
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
