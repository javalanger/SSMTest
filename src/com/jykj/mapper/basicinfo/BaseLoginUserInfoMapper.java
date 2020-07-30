package com.jykj.mapper.basicinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseLoginUserInfoMapper {
	public abstract List<Map<String, Object>> selectUserInfo();
}