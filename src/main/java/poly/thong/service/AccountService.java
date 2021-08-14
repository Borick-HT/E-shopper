package poly.thong.service;

import java.util.List;

import poly.thong.entity.Account;

public interface AccountService {

	public Account findById(String username);

	public List<Account> getAdministrators();

	public List<Account> findAll();
}
