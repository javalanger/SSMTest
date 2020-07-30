package com.jykj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jykj.service.basicinfo.BaseLoginUserInfoService;

@Controller
public class BaseLoginUserInfoController {

	@Autowired
	private BaseLoginUserInfoService baseLoginUserInfoService;
	
	@ResponseBody
	@RequestMapping("selectUserInfo")
	public List<Map<String, Object>> selectUserInfo() {
		return baseLoginUserInfoService.selectUserInfo();
	}
	
	@RequestMapping(value="hello/login")
	@ResponseBody
	public String login(String name) {
		System.out.println("name-->" + name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return JSON.toJSONString(map);
	}
}
