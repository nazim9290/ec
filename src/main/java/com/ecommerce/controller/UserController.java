package com.ecommerce.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.dao.LoginDao;
import com.ecommerce.entity.UserEntity;

@Controller
@RequestMapping("user")
public class UserController {



	@GetMapping("/login")
	public String doLogin(Model model) {
		model.addAttribute("title", "ログイン画面");

		return "user/login";
	}

	@GetMapping("/signup")
	public String userRegister(Model model) {
		
		model.addAttribute("title", "サインアップしてください。");
		return "user/signup";
	}

	@PostMapping("/doLogin")
	public String doLogin(HttpSession session, @ModelAttribute LoginDao loginDao, Model model) {

		UserEntity userEntity = userService.getUserEntityByName(loginForm.getName());


		// loginパスワードのチェック処理
		if ((userEntity != null) && passwordEncode.equals(userEntity.getPassword())) {
			session.setAttribute("loginUser", userEntity);
			return "redirect:/user/list";
		} else {
			model.addAttribute("errorMsg", "ログイン失敗しました。");
			return "login";
		}

	}

	@GetMapping("/logOut")
	public String doOut(HttpSession session, Model model) {
		model.addAttribute("loginForm", new LoginDao());
		session.removeAttribute("loginUser");
		return "login";
	}

}
