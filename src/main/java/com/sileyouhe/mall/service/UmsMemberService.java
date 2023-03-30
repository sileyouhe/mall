package com.sileyouhe.mall.service;


import com.sileyouhe.mall.common.api.CommonResult;

/**
 * membership management
 */
public interface UmsMemberService {

    /**
     * generate auth code and send it to the telephone
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * verify
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
