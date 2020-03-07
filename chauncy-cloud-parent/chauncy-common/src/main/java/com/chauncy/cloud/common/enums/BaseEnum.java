package com.chauncy.cloud.common.enums;

/**
 * @Author cheng
 * @create 2020-03-06 23:47
 *
 * 根 据枚举类某个字段 id  name 等判断该值是否在枚举类中存在
 * 如果要枚举类的参数验证，需要实现该接口
 * 实现后根据需要选择字段进行判断是否存在
 */
public interface BaseEnum {

    boolean isExist(Object field);
}