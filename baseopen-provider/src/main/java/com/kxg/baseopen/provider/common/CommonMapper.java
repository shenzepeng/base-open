package com.kxg.baseopen.provider.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 要写注释呀
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}