package com.chauncy.cloud.core.config.mybatisplus;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author cheng
 * @create 2020-03-07 00:00
 * 配置mybatis的分页插件pageHelper
 */

@Configuration
public class PagePlugin {

    @Bean
    public PageInterceptor pageHelper(){
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        // 当该参数设置为 true 时，会将 RowBounds 中的 offset 参数当成 pageNum 使用，可以用页码和页面大小两个参数进行分页。
        properties.setProperty("offsetAsPageNum","true");
        //当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果
        properties.setProperty("pageSizeZero","true");
        properties.setProperty("rowBoundsWithCount","true");
        //当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页
        properties.setProperty("reasonable","true");
        //配置mysql数据库的方言
        //properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}