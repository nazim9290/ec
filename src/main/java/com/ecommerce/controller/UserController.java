package com.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.ecommerce.form.UserForm;
import com.ecommerce.service.CartService;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;



	@GetMapping("/login")
	public String doLogin(Model model) {
		model.addAttribute("title", "ログイン画面");
		model.addAttribute("loginDao", new LoginDao());
		return "user/login";
	}


	@PostMapping("/doLogin")
	public String doLogin(HttpSession session, HttpServletRequest request, @ModelAttribute LoginDao loginDao,
			Model model) {

		UserEntity userEntity = userService.getUserByEmail(loginDao.getUser_email());


		// loginパスワードのチェック処理
		if ((userEntity != null) && loginDao.getUser_password().equals(userEntity.getUser_password())) {
			session.setAttribute("loginUser", userEntity);
			Object cartCount = cartService.cartCount(userEntity.getUser_id());
			session.setAttribute("cartCount",cartCount );
			return "redirect:/product";
		} else {
			model.addAttribute("errorMsg", "ログイン失敗しました。");
			return "user/login";
		}

	}

	@GetMapping("/logOut")
	public String doOut(HttpSession session, Model model) {
		model.addAttribute("loginDao", new LoginDao());
		session.removeAttribute("loginUser");
		session.removeAttribute("cartCount");
		return "user/login";
	}

	@GetMapping("/signup")
	public String userRegister(Model model) {
		model.addAttribute("userForm", new UserForm());

		model.addAttribute("title", "サインアップしてください。");

		return "user/signup";
	}

	@PostMapping("/insert")
	public String userRegister(@ModelAttribute UserForm userForm) {
		userService.insertUser(userForm);

		return "redirect:/";
	}

}
