package poly.thong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppinCartController {
	@RequestMapping("/cart/view")
	public String cart() {
		return "cart/view";
	}
	
	
}
