package com.sileyouhe.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.sileyouhe.mall.mbg.mapper","com.sileyouhe.mall.dao"})
public class MybatisConfig {
}
