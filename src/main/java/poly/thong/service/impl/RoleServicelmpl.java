package poly.thong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.thong.dao.RoleDao;
import poly.thong.entity.Role;
import poly.thong.service.RoleService;
@Service
public class RoleServicelmpl implements RoleService{
	@Autowired
	RoleDao rdao;
	@Override
	public List<Role> findAll() {
		return rdao.findAll();
	}
	
}
