package com.jykj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("json")
public class JsonController {
	
	@RequestMapping("a")
	@ResponseBody
	public Object getInfo(String str) {
		JSONObject fromObject = JSONObject.fromObject(str);
		return fromObject;
	}
	
}
