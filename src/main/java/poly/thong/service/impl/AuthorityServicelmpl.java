package poly.thong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import poly.thong.dao.AccountDao;
import poly.thong.dao.AuthorityDao;
import poly.thong.entity.Account;
import poly.thong.entity.Authority;
import poly.thong.service.AuthorityService;

@Service
public class AuthorityServicelmpl implements AuthorityService{
	@Autowired
	AuthorityDao adao;
	@Autowired
	AccountDao acdao;
	

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts=acdao.getAdministrators();
		return adao.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return adao.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return adao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		adao.deleteById(id);
	}
	
}
