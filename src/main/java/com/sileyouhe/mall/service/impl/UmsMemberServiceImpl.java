package com.sileyouhe.mall.service.impl;

import com.sileyouhe.mall.common.api.CommonResult;
import com.sileyouhe.mall.service.RedisService;
import com.sileyouhe.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTO_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        /**
         * generate the AuthCode
         */
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        /**
         * associate the code with a telephone number
         */
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTO_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"authentication code is generated successfully");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (!StringUtils.hasLength(authCode)){
            return CommonResult.failed("please enter the Auth code");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result){
            return CommonResult.success(null, "verification success");
        } else{
            return CommonResult.failed("verification failed");
        }
    }
}
