package poly.thong.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.thong.entity.Role;

public interface RoleDao extends JpaRepository<Role, String>{
	
}
