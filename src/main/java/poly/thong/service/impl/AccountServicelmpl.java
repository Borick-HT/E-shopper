package poly.thong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.thong.dao.AccountDao;
import poly.thong.entity.Account;
import poly.thong.service.AccountService;

@Service
public class AccountServicelmpl implements AccountService{
	@Autowired
	AccountDao adao;

	@Override
	public Account findById(String name) {
		// TODO Auto-generated method stub
		return adao.findById(name).get();
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return adao.getAdministrators();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return adao.findAll();
	}
	
	
}
