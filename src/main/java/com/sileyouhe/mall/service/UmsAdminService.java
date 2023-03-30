package com.sileyouhe.mall.service;


import com.sileyouhe.mall.mbg.model.UmsAdmin;
import com.sileyouhe.mall.mbg.model.UmsPermission;

import java.util.List;

public interface UmsAdminService {

    /**
     * get Admin by username
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * register
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * log in
     * @param username
     * @param password
     * @return generated JWT TOKEN
     */
    String login(String username, String password);

    /**
     * get user's permission list
     *
     */
    List<UmsPermission> getPermissionList(Long adminId);

}
