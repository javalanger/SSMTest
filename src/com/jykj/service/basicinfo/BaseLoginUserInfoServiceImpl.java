package com.jykj.service.basicinfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jykj.mapper.basicinfo.BaseLoginUserInfoMapper;


@Service
public class BaseLoginUserInfoServiceImpl implements BaseLoginUserInfoService {

	@Autowired
	private BaseLoginUserInfoMapper baseLoginUserInfoMapper;
	
	@Override
	public List<Map<String, Object>> selectUserInfo() {
		return baseLoginUserInfoMapper.selectUserInfo();
	}

}
