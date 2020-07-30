package com.jykj.service.basicinfo;

import java.util.List;
import java.util.Map;

public interface BaseLoginUserInfoService {
	public abstract List<Map<String, Object>> selectUserInfo();
}
