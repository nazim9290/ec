package com.ecommerce.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.dao.LoginDao;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;



	@GetMapping("/login")
	public String doLogin(Model model) {
		model.addAttribute("title", "ログイン画面");
		model.addAttribute("loginDao", new LoginDao());
		return "user/login";
	}


	@PostMapping("/doLogin")
	public String doLogin(HttpSession session, @ModelAttribute LoginDao loginDao, Model model) {

		UserEntity userEntity = userService.getUserEntityByEmail(loginDao.getUser_email());

		// loginパスワードのチェック処理
		if ((userEntity != null) && loginDao.getUser_password().equals(userEntity.getUser_password())) {
			session.setAttribute("loginUser", userEntity);
			return "redirect:/";
		} else {
			model.addAttribute("errorMsg", "ログイン失敗しました。");
			return "user/login";
		}

	}

	@GetMapping("/logOut")
	public String doOut(HttpSession session, Model model) {
		model.addAttribute("loginDao", new LoginDao());
		session.removeAttribute("loginUser");
		return "user/login";
	}

	@GetMapping("/signup")
	public String userRegister(Model model) {

		model.addAttribute("title", "サインアップしてください。");

		return "user/signup";
	}

}
