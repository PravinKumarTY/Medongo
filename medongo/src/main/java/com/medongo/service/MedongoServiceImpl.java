package com.medongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medongo.dao.MedongoDao;
import com.medongo.dto.UserInfoDto;
@Service
public class MedongoServiceImpl implements MedongoService{
	@Autowired
	private MedongoDao dao;

	@Override
	public boolean registerUser(UserInfoDto userInfo) {
		return dao.registerUser(userInfo);
	}
}
