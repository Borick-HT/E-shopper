package poly.thong.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.thong.entity.Account;
import poly.thong.entity.Role;
import poly.thong.service.AccountService;
import poly.thong.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
	@GetMapping
	public List<Account>FindAll(@RequestParam("admin") Optional<Boolean> admin){
		if (admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();

	}

}
