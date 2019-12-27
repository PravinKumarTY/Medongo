package com.medongo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medongo.dto.PatientDto;
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
	
	@Override
	public UserInfoDto userLogin(String userEmail,String userPassword) {
		try {
			EntityManager entityManager=entityManagerFactory.createEntityManager();
			String jpQl="from UserInfoDto where userEmail=:email and userPassword=:password";
			Query query=entityManager.createQuery(jpQl);
			query.setParameter("email", userEmail);
			query.setParameter("password",userPassword);
			return (UserInfoDto) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean registerPatient(PatientDto patientDto) {
		
		String patIdStr="P_00";
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction tranjaction=entityManager.getTransaction();
		
		
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(PatientDto.class)));
		Long count=entityManager.createQuery(cq).getSingleResult();
		
		boolean flag=false;
		try {
			++count;
			patIdStr=patIdStr+count;
			patientDto.setPatId(patIdStr);
			
			tranjaction.begin();
			entityManager.persist(patientDto);
			tranjaction.commit();	
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<UserInfoDto> getDoctors() {
		List<UserInfoDto> doctorList=null;
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		String jpQl="from UserInfoDto where userRole='doctor'";
		Query query=entityManager.createQuery(jpQl);
		doctorList=query.getResultList();
		return doctorList;
	}
	
    public List<PatientDto> vewRegisteredPat(){
    	EntityManager entityManager=entityManagerFactory.createEntityManager();
    	String jpQl="from PatientDto";
    	Query query=entityManager.createQuery(jpQl);
    	return query.getResultList();
    }
}
