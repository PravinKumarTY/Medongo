package com.medongo.dao;

import com.medongo.dto.UserInfoDto;

public interface MedongoDao {
	public boolean registerUser(UserInfoDto userInfo);
}
