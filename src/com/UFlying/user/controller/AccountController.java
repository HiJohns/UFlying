package com.UFlying.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.UFlying.exception.ServiceException;
import com.UFlying.user.entity.base.EnterpriseAccount;
import com.UFlying.user.entity.base.IndividualAccount;
import com.UFlying.user.entity.request.RequestGetVerifyCode;
import com.UFlying.user.entity.response.ResponseBasic;
import com.UFlying.user.form.FormChangePassword;
import com.UFlying.user.form.FormLogin;
import com.UFlying.user.form.FormRegisterEnterpriseAccount;
import com.UFlying.user.form.FormRegisterIndividualAccount;
import com.UFlying.user.form.FormResetPassword;
import com.UFlying.user.form.FormUpdateEnterpriseAccount;
import com.UFlying.user.form.FormUpdateIndividualAccount;
import com.UFlying.user.service.AccountService;
import com.UFlying.user.service.SmsService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private SmsService smsService;

	/** 用户票证在cookie中的有效期为15天 */
	private static final int COOKIE_TOKEN_MAX_AGE = 15 * 24 * 60 * 60;

	// 登录

	/** 进入登录页 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@CookieValue(required = false) String token, HttpServletResponse response, Model model) {
		Object account = null;
		// 若cookie中存在token，则表示该用户登录过，只需要验证token即可
		if (StringUtils.isNotBlank(token)) {
			IndividualAccount individualAccount = accountService.getIndividualAccountByToken(token);
			if (individualAccount != null) {
				account = individualAccount;
				token = individualAccount.getToken();
			} else {
				EnterpriseAccount enterpriseAccount = accountService.getEnterpriseAccountByToken(token);
				if (enterpriseAccount != null) {
					account = enterpriseAccount;
					token = enterpriseAccount.getToken();
				}
			}
			// 若cookie中存在token，但根据token未找到用户，则表示该token不合法，删除之
			if (account == null) {
				Cookie cookie = new Cookie("token", null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		if (account != null) {
			model.addAttribute("account", account);
			// 登录成功，保存用户token到cookie中，并重设cookie有效期
			Cookie cookie = new Cookie("token", token);
			cookie.setMaxAge(COOKIE_TOKEN_MAX_AGE);
			cookie.setPath("/");
			response.addCookie(cookie);
			if (account instanceof IndividualAccount) {
				return "redirect:/individual_account";
//			} else if (account instanceof EnterpriseAccount) {
//				return "redirect:/enterprise_account";
			}
		}
		return "user/login";
	}

	/** 登录，自动区分个人用户、企业用户 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute FormLogin form, HttpServletResponse response, Model model) {
		Object account = null;
		String token = null;
		try {
			account = accountService.login(form);
		} catch (ServiceException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("form", form);
		}
		if (account != null) {
			if (account instanceof IndividualAccount) {
				token = IndividualAccount.class.cast(account).getToken();
				Cookie cookie = new Cookie("token", token);
				cookie.setMaxAge(COOKIE_TOKEN_MAX_AGE);
				cookie.setPath("/");
				response.addCookie(cookie);
				return "redirect:/individual_account";
//			} else if (account instanceof EnterpriseAccount) {
//				token = EnterpriseAccount.class.cast(account).getToken();
//				Cookie cookie = new Cookie("token", token);
//				cookie.setMaxAge(COOKIE_TOKEN_MAX_AGE);
//				cookie.setPath("/");
//				response.addCookie(cookie);
//				return "redirect:/enterprise_account";
			}
		}
		return "user/login";
	}

	/** 注册页 - 提示用户选择个人用户或企业用户 */
	@RequestMapping(value = "/accountType", method = RequestMethod.GET)
	public String register() {
		return "user/accountType";
	}

	// 注册步骤1

	/** 注册页 - 个人用户，进入注册初始页 */
	@RequestMapping(value = "/individual_register", method = RequestMethod.GET)
	public String registerIndividual() {
		return "user/individual_register";
	}

	/** 注册页 - 企业用户，进入注册初始页 */
	@RequestMapping(value = "/enterprise_register", method = RequestMethod.GET)
	public String registerEnterprise() {
		return "user/enterprise_register";
	}

	// 注册步骤2

	/** 注册页 - 个人用户，保存基本信息，并进入高级信息初始页 */
	@RequestMapping(value = "/individual_register", method = RequestMethod.POST)
	public String registerIndividual(@ModelAttribute FormRegisterIndividualAccount form, Model model, HttpServletResponse response) {
		IndividualAccount account = null;
		try {
			account = accountService.registerIndividualAccount(form);
		} catch (ServiceException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("form", form);
		}
		if (account != null) {
			model.addAttribute("account", account);
			String token = account.getToken();
			// 保存用户token到cookie中
			Cookie cookie = new Cookie("token", token);
			cookie.setMaxAge(COOKIE_TOKEN_MAX_AGE);
			cookie.setPath("/");
			response.addCookie(cookie);
			return "user/individual_account";
		}
		return "user/individual_register";
	}

	/** 注册页 - 企业用户，保存基本信息，并进入高级信息初始页 */
	@RequestMapping(value = "/enterprise_register", method = RequestMethod.POST)
	public String registerEnterprise(@ModelAttribute FormRegisterEnterpriseAccount form, Model model, HttpServletResponse response) {
		EnterpriseAccount account = null;
		try {
			account = accountService.registerEnterpriseAccount(form);
		} catch (ServiceException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("form", form);
		}
		if (account != null) {
			model.addAttribute("account", account);
			String token = account.getToken();
			// 保存用户token到cookie中
			Cookie cookie = new Cookie("token", token);
			cookie.setMaxAge(COOKIE_TOKEN_MAX_AGE);
			cookie.setPath("/");
			response.addCookie(cookie);
			return "user/enterprise_account";
		}
		return "user/enterprise_register";
	}

	// 注册步骤3
	@RequestMapping(value = "/individual_complete", method = RequestMethod.GET)
	public String completeIndividual(@CookieValue(required = false) String token, Model model) {
			IndividualAccount account = accountService.getIndividualAccountByToken(token);
			if (account != null) {
				model.addAttribute("form", accountService.individualAccountToForm(account));
				return "user/individual_complete";
			}
			return "redirect:/login";
	}

	@RequestMapping(value = "/enterprise_complete", method = RequestMethod.GET)
	public String completeEnterprise(@CookieValue(required = false) String token, Model model) {
			EnterpriseAccount account = accountService.getEnterpriseAccountByToken(token);
			if (account != null) {
				model.addAttribute("form", accountService.enterpriseAccountToForm(account));
				return "user/enterprisel_complete";
			}
			return "redirect:/login";
	}
	/** 注册页 - 个人用户，完善信息，完成后进入个人中心 */
//	@RequestMapping(value = "/individual_complete", method = RequestMethod.POST)
//	public String completeIndividual(@CookieValue(required = false) String token, @ModelAttribute FormUpdateIndividualAccount form,
//			HttpServletResponse response, Model model) {
//		IndividualAccount account = null;
//		try {
//			account = accountService.completeIndividualAccount(form, token);
//		} catch (ServiceException e) {
//			model.addAttribute("message", e.getMessage());
//			model.addAttribute("form", form);
//		}
//		if (account != null) {
//			model.addAttribute("account", account);
//			Cookie cookie = new Cookie("token", account.getToken());
//			cookie.setMaxAge(COOKIE_TOKEN_MAX_AGE);
//			cookie.setPath("/");
//			response.addCookie(cookie);
//			return "redirect:/individual_account";
//		}
//		return "user/individual_complete";
//	}

	/** 注册页 - 企业用户，保存高级信息，完成后进入个人中心 */
//	@RequestMapping(value = "/enterprise_complete", method = RequestMethod.POST)
//	public String completeEnterprise(@CookieValue(required = false) String token, @ModelAttribute FormUpdateEnterpriseAccount form,
//			HttpServletResponse response, Model model) {
//		EnterpriseAccount account = null;
//		try {
//			account = accountService.completeEnterpriseAccount(form, token);
//		} catch (ServiceException e) {
//			model.addAttribute("message", e.getMessage());
//			model.addAttribute("form", form);
//		}
//		if (account != null) {
//			model.addAttribute("account", account);
//			Cookie cookie = new Cookie("token", account.getToken());
//			cookie.setMaxAge(COOKIE_TOKEN_MAX_AGE);
//			cookie.setPath("/");
//			response.addCookie(cookie);
//			return "redirect:/enterprise_account";
//		}
//		return "account/enterprise_complete";
//	}

	// 用户中心
	/** 个人用户个人中心 */
	@RequestMapping("/individual_account")
	public String individualAccount(@CookieValue(required = false) String token, Model model) {
		IndividualAccount account = accountService.getIndividualAccountByToken(token);
		if (account != null) {
			model.addAttribute("account", account);
			return "user/individual_account";
		}
		return "redirect:/login";
	}

	/** 企业用户个人中心 */
	@RequestMapping("/enterprise_account")
	public String enterpriseAccount(@CookieValue(required = false) String token, Model model) {
		EnterpriseAccount account = accountService.getEnterpriseAccountByToken(token);
		if (account != null) {
			model.addAttribute("account", account);
			return "user/enterprise_account";
		}
		return "redirect:/login";
	}

//	/** 修改个人用户信息，初始页 */
//	@RequestMapping(value = "/individual_edit", method = RequestMethod.GET)
//	public String individualEdit(@CookieValue(required = false) String token, Model model) {
//		IndividualAccount account = accountService.getIndividualAccountByToken(token);
//		if (account != null) {
//			model.addAttribute("account", account);
//			return "account/individual_edit";
//		}
//		return "redirect:/login";
//	}
//
//	/** 修改企业用户信息，初始页 */
//	@RequestMapping(value = "/enterprise_edit", method = RequestMethod.GET)
//	public String enterpriseEdit(@CookieValue(required = false) String token, Model model) {
//		EnterpriseAccount account = accountService.getEnterpriseAccountByToken(token);
//		if (account != null) {
//			model.addAttribute("account", account);
//			return "account/enterprise_edit";
//		}
//		return "redirect:/login";
//	}

	/** 修改个人用户信息 */
	@RequestMapping(value = "/individual_edit", method = RequestMethod.POST)
	public String individualEdit(@CookieValue(required = false) String token, RedirectAttributes attributes,
			@ModelAttribute FormUpdateIndividualAccount form, Model model) {
		IndividualAccount account = accountService.getIndividualAccountByToken(token);
		if (account == null) {
			return "redirect:/login";
		}
		try {
			accountService.updateIndividualAccount(form, token);
		} catch (ServiceException e) {
			model.addAttribute("account", account);
			model.addAttribute("message", e.getMessage());
			model.addAttribute("form", form);
			return "user/individual_complete";
		}
		attributes.addFlashAttribute("message", "保存成功");
		return "redirect:/individual_account";
	}

	/** 修改企业用户信息 */
	@RequestMapping(value = "/enterprise_edit", method = RequestMethod.POST)
	public String enterpriseEdit(@CookieValue(required = false) String token, RedirectAttributes attributes,
			@ModelAttribute FormUpdateEnterpriseAccount form, Model model) {
		EnterpriseAccount account = accountService.getEnterpriseAccountByToken(token);
		if (account == null) {
			return "redirect:/login";
		}
		try {
			accountService.updateEnterpriseAccount(form, token);
		} catch (ServiceException e) {
			model.addAttribute("account", account);
			model.addAttribute("message", e.getMessage());
			model.addAttribute("form", form);
			return "user/enterprise_edit";
		}
		attributes.addFlashAttribute("message", "保存成功");
		return "redirect:/enterprise_account";
	}

	/** 显示个人电子会员证 */
	@RequestMapping(value = "/individual_card", method = RequestMethod.GET)
	public String individualCard(@CookieValue(required = false) String token, Model model) {
		IndividualAccount account = accountService.getIndividualAccountByToken(token);
		if (account == null) {
			return "redirect:/login";
		}
		model.addAttribute("account", account);
		return "user/individual_card";
	}

	/** 显示企业电子会员证 */
	@RequestMapping(value = "/enterprise_card", method = RequestMethod.GET)
	public String enterpriseCard(@CookieValue(required = false) String token, Model model) {
		EnterpriseAccount account = accountService.getEnterpriseAccountByToken(token);
		if (account == null) {
			return "redirect:/login";
		}
		model.addAttribute("account", account);
		return "user/enterprise_card";
	}

	/** 发送手机验证码，用于注册 */
	@RequestMapping(value = "/register_verify_code", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBasic registerVerifyCode(@RequestParam String phone) {
//		boolean phoneExists = accountService.checkPhoneExists(phone);
//		if (phoneExists) {
//			ResponseBasic response = new ResponseBasic();
//			response.setStatus(-1);
//			response.setReason("该手机号已被注册");
//			return response;
//		}
		RequestGetVerifyCode request = new RequestGetVerifyCode();
		request.setPhoneNumber(phone);
		return smsService.sendVerifyCode(request);
	}

	/** 发送手机验证码，用于重置密码 */
	@RequestMapping(value = "/reset_password_verify_code", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBasic resetPasswordVerifyCode(@RequestParam String phone) {
		boolean phoneExists = accountService.checkPhoneExists(phone);
		if (!phoneExists) {
			ResponseBasic response = new ResponseBasic();
			response.setStatus(-1);
			response.setReason("该手机号未被注册");
			return response;
		}
		RequestGetVerifyCode request = new RequestGetVerifyCode();
		request.setPhoneNumber(phone);
		return smsService.sendVerifyCode(request);
	}

	/** 登出 */
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/login";
	}

	/** 修改密码，初始页 */
	@RequestMapping(value = "/change_password", method = RequestMethod.GET)
	public String changePassword() {
		return "user/change_password";
	}

	/** 修改密码 */
	@RequestMapping(value = "/change_password", method = RequestMethod.POST)
	public String changePassword(@CookieValue String token, RedirectAttributes attributes, HttpServletResponse response,
			FormChangePassword form, Model model) {
		try {
			accountService.changePassword(form, token);
		} catch (ServiceException e) {
			model.addAttribute("message", e.getMessage());
			return "user/change_password";
		}
		attributes.addFlashAttribute("message", "密码修改成功！请重新登录");
		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/login";
	}

	/** 重置密码，初始页 */
	@RequestMapping(value = "/reset_password", method = RequestMethod.GET)
	public String resetPassword() {
		return "user/reset_password";
	}

	/** 重置密码 */
	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public String resetPassword(RedirectAttributes attributes, @ModelAttribute FormResetPassword form, Model model) {
		try {
			accountService.resetPassword(form);
		} catch (ServiceException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("form", form);
			return "user/reset_password";
		}
		attributes.addFlashAttribute("message", "密码重置成功！请重新登录");
		return "redirect:/login";
	}

	/** 验证手机是否被注册 */
	@RequestMapping(value = "/check_mobile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBasic checkMobile(@RequestParam String phone) {
		boolean phoneExists = accountService.checkPhoneExists(phone);
		ResponseBasic response = new ResponseBasic();
		response.setStatus(0);
		response.setReason(phoneExists?"1":"0");
		return response;
	}

}
