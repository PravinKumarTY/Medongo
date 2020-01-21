package com.medongo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medongo.dto.AppointmentDto;
import com.medongo.dto.MasterDto;
import com.medongo.dto.MedicineDto;
import com.medongo.dto.PatientDto;
import com.medongo.dto.Prescription;
import com.medongo.dto.UserInfoDto;
import com.medongo.dto.VendorDto;

@Repository
public class MedongoDaoImpl implements MedongoDao {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean registerUser(UserInfoDto userInfo) {
		Double age = 0.0;
		String userIdStr = "K_00";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tranjaction = entityManager.getTransaction();

		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(UserInfoDto.class)));
		Long count = entityManager.createQuery(cq).getSingleResult();

		boolean flag = false;
		try {
			++count;
			userIdStr = userIdStr + count;
			userInfo.setUserId(userIdStr);

			Date dob = userInfo.getDob();
			if (dob != null) {
				age = getAge(dob);
			}
			userInfo.setAge(age);

			String password = autoGeneratePassword();
			userInfo.setUserPassword(password);

			tranjaction.begin();
			entityManager.persist(userInfo);
			tranjaction.commit();
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public UserInfoDto userLogin(String userEmail, String userPassword) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			String jpQl = "from UserInfoDto where userEmail=:email and userPassword=:password";
			Query query = entityManager.createQuery(jpQl);
			query.setParameter("email", userEmail);
			query.setParameter("password", userPassword.trim());
			return (UserInfoDto) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean registerPatient(PatientDto patientDto) {

		String patIdStr = "P_00";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tranjaction = entityManager.getTransaction();

		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(PatientDto.class)));
		Long count = entityManager.createQuery(cq).getSingleResult();

		boolean flag = false;
		try {
			++count;
			patIdStr = patIdStr + count;
			patientDto.setPatId(patIdStr);

			tranjaction.begin();
			entityManager.persist(patientDto);
			tranjaction.commit();
			if (createAppointment(patientDto)) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfoDto> getDoctors() {
		List<UserInfoDto> doctorList = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpQl = "from UserInfoDto where userRole='doctor'";
		Query query = entityManager.createQuery(jpQl);
		doctorList = query.getResultList();
		return doctorList;
	}

	@SuppressWarnings("unchecked")
	public List<PatientDto> vewRegisteredPat() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpQl = "from PatientDto";
		Query query = entityManager.createQuery(jpQl);
		return query.getResultList();
	}

	// method for auto calculate age.
	public static Double getAge(Date date) {
		LocalDate localDob = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currDate = LocalDate.now();
		Period period = Period.between(localDob, currDate);
		String str = period.getYears() + "." + period.getMonths();
		return Double.parseDouble(str);
	}

	// method for auto generate password.
	public static String autoGeneratePassword() {
		String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
		String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
		String numbers = RandomStringUtils.randomNumeric(2);
		String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
		String totalChars = RandomStringUtils.randomAlphanumeric(2);
		String combinedChars = upperCaseLetters.concat(lowerCaseLetters).concat(numbers).concat(specialChar)
				.concat(totalChars);
		List<Character> pwdChars = combinedChars.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Collections.shuffle(pwdChars);
		String password = pwdChars.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
		return password;
	}

	@Override
	public boolean addVendor(VendorDto vendor) {
		String vendorIdStr = "V_00";
		boolean flag = false;

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tranjaction = entityManager.getTransaction();

		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(VendorDto.class)));
		Long count = entityManager.createQuery(cq).getSingleResult();
		try {
			++count;
			vendorIdStr = vendorIdStr + count;
			vendor.setVendorId(vendorIdStr);
			tranjaction.begin();
			entityManager.persist(vendor);
			tranjaction.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorDto> getAllSubCenters() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpQl = "from VendorDto";
		Query query = entityManager.createQuery(jpQl);
		return query.getResultList();
	}

	// Method for create appointment.
	public boolean createAppointment(PatientDto patient) {
		AppointmentDto appointment = new AppointmentDto();
		String apptIdStr = "APPT_000";

		boolean flag = false;
		String doctorId = patient.getDoctorId();
		String vendorId = "";

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tranjaction = entityManager.getTransaction();

		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(AppointmentDto.class)));
		Long count = entityManager.createQuery(cq).getSingleResult();

		try {
			String jpql = "from UserInfoDto where userId=:doctorId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("doctorId", doctorId);
			UserInfoDto userDto = (UserInfoDto) query.getSingleResult();
			vendorId = userDto.getVendorId();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			++count;
			apptIdStr = apptIdStr + count;
			Date apptDate = new Date();

			appointment.setApptId(apptIdStr);
			appointment.setPartyIdFrom(patient.getPatId());
			appointment.setPartyIdTo(doctorId);
			appointment.setVendorId(vendorId);
			appointment.setApptDate(apptDate);
			appointment.setStatusCode(1);

			tranjaction.begin();
			entityManager.persist(appointment);
			tranjaction.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public List<MasterDto> getAllAppointments(String userId) {
		List<MasterDto> masterDtoList = new ArrayList<MasterDto>();
		Map<String, AppointmentDto> patApptMap = new HashMap<String, AppointmentDto>();

		Set<String> patIdSet = new HashSet<String>();

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		String jpQl = "from AppointmentDto where partyIdTo=:doctorId and statusCode=1";

		Query query = entityManager.createQuery(jpQl);
		query.setParameter("doctorId", userId);
		List<AppointmentDto> appList = query.getResultList();

		for (AppointmentDto appt : appList) {
			patApptMap.put(appt.getPartyIdFrom(), appt);
			patIdSet.add(appt.getPartyIdFrom());
		}

		CriteriaQuery<PatientDto> criteriaQuery = criteriaBuilder.createQuery(PatientDto.class);
		Root<PatientDto> root = criteriaQuery.from(PatientDto.class);
		criteriaQuery.select(root).where(root.get("patId").in(patIdSet));
		List<PatientDto> patientList = entityManager.createQuery(criteriaQuery).getResultList();

		for (PatientDto patient : patientList) {
			if (patApptMap.containsKey(patient.getPatId())) {
				AppointmentDto appointment = patApptMap.get(patient.getPatId());
				MasterDto master = new MasterDto();
				master.setApptId(appointment.getApptId());
				master.setApptDate(appointment.getApptDate());
				master.setPartyIdFrom(appointment.getPartyIdFrom());
				master.setPartyIdTo(appointment.getPartyIdTo());
				master.setStatusCode(appointment.getStatusCode());
				master.setVendorId(appointment.getVendorId());

				master.setPatId(patient.getPatId());
				master.setAddress(patient.getAddress());
				master.setPatName(patient.getPatName());
				master.setPhoneNo(patient.getPhoneNo());
				master.setDoctorId(patient.getDoctorId());
				master.setDob(patient.getDob());
				master.setHeight(patient.getHeight());
				master.setAge(patient.getAge());
				master.setBloodPressure(patient.getBloodPressure());
				master.setSymptoms(patient.getSymptoms());
				master.setSugar(patient.getSugar());
				master.setGender(patient.getGender());
				master.setRespiratoryRate(patient.getRespiratoryRate());
				master.setFeverType(patient.getFever());
				master.setWeight(patient.getWeight());
				master.setEmail(patient.getEmail());

				masterDtoList.add(master);
			}
		}
		return masterDtoList;
	}

	@Override
	public boolean submitConsult(MasterDto masterDto) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		AppointmentDto appointment = entityManager.find(AppointmentDto.class, masterDto.getApptId());

		List<MedicineDto> prescriptionList = masterDto.getPrescriptionDet();

		boolean flag = false;

		if (appointment != null) {

			try {
				List<Prescription> preDtoList = new ArrayList<Prescription>();
				for (MedicineDto medicine : prescriptionList) {
					Prescription prescription = new Prescription();
					Integer totalTablets = 0, totalInjection = 0;
					if (medicine.getMedicineType().equals("Tablet")) {
						if (medicine.getFreequency().equals("1-1-1")) {
							totalTablets = medicine.getDays() * 3;
						} else if (medicine.getFreequency().equals("1-1-0") || medicine.getFreequency().equals("1-0-1")
								|| medicine.getFreequency().equals("0-1-1")) {
							totalTablets = medicine.getDays() * 2;
						} else if (medicine.getFreequency().equals("1-0-0") || medicine.getFreequency().equals("0-1-0")
								|| medicine.getFreequency().equals("0-0-1")) {
							totalTablets = medicine.getDays() * 1;
						}
						prescription.setTablets(medicine.getMedicineName());
					}

					if (medicine.getMedicineType().equals("Injection")) {
						if (medicine.getFreequency().equals("1-1-1")) {
							totalInjection = medicine.getDays() * 3;
						} else if (medicine.getFreequency().equals("1-1-0") || medicine.getFreequency().equals("1-0-1")
								|| medicine.getFreequency().equals("0-1-1")) {
							totalInjection = medicine.getDays() * 2;
						} else if (medicine.getFreequency().equals("1-0-0") || medicine.getFreequency().equals("0-1-0")
								|| medicine.getFreequency().equals("0-0-1")) {
							totalInjection = medicine.getDays() * 1;
						}
						prescription.setInjections(medicine.getMedicineName());
					}
					if(medicine.getMedicineType().equals("Drop")) {
						prescription.setDrops(medicine.getMedicineName());
					}
					if(medicine.getMedicineType().equals("Syrup")) {
						prescription.setSyrups(medicine.getMedicineName());
					}

					prescription.setMedicineType(medicine.getMedicineType());
					prescription.setTotalTablets(totalTablets);
					prescription.setTotalInjection(totalInjection);
					prescription.setFreequency(medicine.getFreequency());
					prescription.setAppointment(appointment);
					preDtoList.add(prescription);
				}
				transaction.begin();
				appointment.setStatusCode(0);
				for (Prescription preDto : preDtoList) {
					entityManager.persist(preDto);
				}
				transaction.commit();
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<MasterDto> getAllUsers() {
		List<MasterDto> masterDtoList = new ArrayList<MasterDto>();
		List<String> vendorIdList = new ArrayList<String>();
		Map<String, VendorDto> vendorMap = new HashMap<String, VendorDto>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from UserInfoDto where userRole='doctor' or userRole='operator'";
		Query userQuery = entityManager.createQuery(jpql);
		List<UserInfoDto> userInfoList = userQuery.getResultList();
		if (userInfoList != null) {
			for (UserInfoDto userInfo : userInfoList) {
				vendorIdList.add(userInfo.getVendorId());
			}
		}

		String vendorJpql = "from VendorDto where vendorId in (:vendorId)";
		Query vendorQuery = entityManager.createQuery(vendorJpql);
		vendorQuery.setParameter("vendorId", vendorIdList);
		List<VendorDto> vendorList = vendorQuery.getResultList();

		if (vendorList != null) {
			for (VendorDto vendor : vendorList) {
				vendorMap.put(vendor.getVendorId(), vendor);
			}
		}

		for (UserInfoDto userInfo : userInfoList) {
			if (vendorMap.containsKey(userInfo.getVendorId())) {
				MasterDto masterDtoClass = new MasterDto();
				masterDtoClass.setUserId(userInfo.getUserId());
				masterDtoClass.setUserName(userInfo.getUserName());
				masterDtoClass.setEmail(userInfo.getUserId());
				masterDtoClass.setGender(userInfo.getGender());
				masterDtoClass.setUserRole(userInfo.getUserRole());
				masterDtoClass.setAge(userInfo.getAge());
				masterDtoClass.setPhoneNo(userInfo.getPhoneNo());
				masterDtoClass.setVendorId(userInfo.getVendorId());
				masterDtoClass.setVendorName(vendorMap.get(userInfo.getVendorId()).getVendorName());
				masterDtoList.add(masterDtoClass);
			}
		}
		if (masterDtoList.size() > 0) {
			return masterDtoList;
		} else {
			return null;
		}
	}

	@Override
	public List<MasterDto> generateReport(Date startDate, Date endDate, String vendorId, String doctorId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDateStr = sdf.format(startDate) + " " + "00:00:00";
		String endDateStr = sdf.format(endDate) + " " + "23:59:59";
		Date start=null, end=null;
		
		try {
			start = dateFormat.parse(startDateStr);
			end = dateFormat.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<MasterDto> masterDtoList = new ArrayList<MasterDto>();
		List<String> patIdList = new ArrayList<String>();

		String jpql = "from AppointmentDto where (apptDate>=:stDate and apptDate<=:enDate) and statusCode=0 and vendorId=:vId and partyIdTo=:pIdTo";
		Query query = entityManager.createQuery(jpql);

		query.setParameter("stDate", start);
		query.setParameter("enDate", end);
		query.setParameter("vId", vendorId);
		query.setParameter("pIdTo", doctorId);
		List<AppointmentDto> apptList = query.getResultList();
		
		if (apptList.size()>0) {
			for (AppointmentDto appt : apptList) {
				patIdList.add(appt.getPartyIdFrom());
			}

			String patientJpql = "from PatientDto where patId in(:patientId)";
			Query patientQuery=entityManager.createQuery(patientJpql);
			patientQuery.setParameter("patientId", patIdList);
			List<PatientDto> patientList=patientQuery.getResultList();
			for(PatientDto patient:patientList) {
				MasterDto masterDto=new MasterDto();
				masterDto.setPatName(patient.getPatName());
				masterDto.setAddress(patient.getAddress());
				masterDto.setGender(patient.getGender());
				masterDto.setPhoneNo(patient.getPhoneNo());
				masterDto.setPatId(patient.getPatId());
				masterDto.setAge(patient.getAge());
				masterDto.setSymptoms(patient.getSymptoms());
				masterDto.setHeight(patient.getHeight());
				masterDto.setFever(patient.getFeverType());
				masterDtoList.add(masterDto);
			}
			return masterDtoList;
		}else {
			return null;
		}
	}

	@Override
	public List<MasterDto> generateMedicinesReport(String vendorId, String doctorId) {
		List<MasterDto> masterDtoList=new ArrayList<MasterDto>();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String apptJpql="from AppointmentDto where statusCode=0 and vendorId=:vId and partyIdTo=:pIdTo";
		Query apptQuery=entityManager.createQuery(apptJpql);
		apptQuery.setParameter("vId", vendorId);
		apptQuery.setParameter("pIdTo", doctorId);
		List<AppointmentDto> appointmentList=apptQuery.getResultList();
		if(appointmentList.size()>0) {
			for(AppointmentDto appointment:appointmentList) {
				MasterDto masterDto=new MasterDto();
				masterDto.setApptId(appointment.getApptId());
				List<MedicineDto> medicinesDtoList=new ArrayList<MedicineDto>();
				
				List<Prescription> prescriptions=appointment.getPrescreption();
				//masterDto.setMedicinesList(prescriptions);
				for(Prescription presctiption:prescriptions) {
					MedicineDto medicines=new MedicineDto();
					
					medicines.setMedicineType(presctiption.getMedicineType());
					if(presctiption.getMedicineType().equals("Tablet")) {
						medicines.setMedicineName(presctiption.getTablets());
					}
					if(presctiption.getMedicineType().equals("Injection")) {
						medicines.setMedicineName(presctiption.getInjections());
					}
					if(presctiption.getMedicineType().equals("Drop")) {
						medicines.setMedicineName(presctiption.getDrops());
					}
					if(presctiption.getMedicineType().equals("Syrup")) {
						medicines.setMedicineName(presctiption.getSyrups());
					}
					medicines.setFreequency(presctiption.getFreequency());
					medicinesDtoList.add(medicines);
				}
				masterDto.setPrescriptionDet(medicinesDtoList);
				masterDtoList.add(masterDto);
			}
			return masterDtoList;
		}else {
			return null;
		}
	}
}
