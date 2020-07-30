package com.jykj.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("param")
public class ParamTestController {
	public static Map<String, Object> getBackMessage() {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("msg", "success");
		return resultMap;
	}
	
	@RequestMapping("/{id}/{c}")
	@ResponseBody
	public Map<String, Object> test(@PathVariable String id,@RequestParam(value="c",required=false) String code) {
		Map<String, Object> resultMap = getBackMessage();
		resultMap.put("id", id);
		resultMap.put("code", code);
		return resultMap;
	}
	
	@Test
	public void test() {
		int arr[] = {15,33,65,8};
		print(arr);
	}
	
	public void print(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
}
