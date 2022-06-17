package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Admin;
import com.Maktab.Final.model.entity.enums.Role;
import com.Maktab.Final.model.exception.LogicErrorException;
import com.Maktab.Final.model.repository.AdminRepository;
import com.Maktab.Final.model.service.serviceInterface.AdminServiceInterface;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class AdminService implements AdminServiceInterface {

	private final AdminRepository adminRepository;

	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin findAdminById(Integer id) {
		Admin admin = adminRepository.findAdminById(id);
		if (admin == null) throw new LogicErrorException("admin not found");
		admin.setPassword(null);
		return admin;
	}

	public void create(Admin admin) {
		admin.setRegisterTime(LocalDateTime.now());
		admin.setRole(Role.ROLE_ADMIN);
		adminRepository.save(admin);
	}
}
