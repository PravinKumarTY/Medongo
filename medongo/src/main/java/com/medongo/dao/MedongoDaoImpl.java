package com.medongo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medongo.dto.UserInfoDto;
@Repository
public class MedongoDaoImpl implements MedongoDao{
	@Autowired
    private EntityManagerFactory entityManagerFactory;
	@Override
	public boolean registerUser(UserInfoDto userInfo) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction tranjaction=entityManager.getTransaction();
		boolean flag=false;
		try {
			tranjaction.begin();
			entityManager.persist(userInfo);
			tranjaction.commit();	
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
