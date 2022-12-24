package edu.school21.cinema.controlers;

import edu.school21.cinema.listeners.UserRegistrationEvent;
import edu.school21.cinema.models.*;
import edu.school21.cinema.services.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

@Controller
@RequestMapping("/")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private LogAuthService logAuthService;

	@Autowired
	private FilmService filmService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private StorageService storageService;

	//rendering security LOGIN page
	@GetMapping("login")
	public String login() {
		logger.info("GET: endpoint 'login.....'");
		return "login";
	}

	//authentification
//	@GetMapping ("/profile")
//	public String openProfile(Authentication a,
//							  HttpServletResponse response,
//							  HttpServletRequest request,
//							  Model model) {
//		logger.info("Get: endpoint 'login'");
//		User user = userService.findUserByEmail(a.getName());
//		String status = user.getProfileStatus().toString();
//		if (user != null && status.equals("CONFIRMED")) {
//			Logs log = new Logs();
//			LocalTime now = LocalTime.now();
//			LocalDate now1 = LocalDate.now();
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//			String data = "" + now1.getMonth() + " " + now1.getDayOfMonth() + ", " + now1.getYear() + "";
//			log.setTime(now.format(formatter));
//			log.setDate(data);
//			log.setEmail(user.getEmail());
//			log.setIp(request.getRemoteAddr());
//			logAuthService.save(log);
//			Cookie cookie = new Cookie("email", user.getEmail());
//			cookie.setMaxAge(60*10);
//			response.addCookie(cookie);
//			model.addAttribute("lstAvatar", storageService.loadAllAvatars(user));
//			model.addAttribute("logs", logAuthService.findAllLogsByEmail(user.getEmail()));
//			model.addAttribute("user", user);
//			return "profile";
//		}
//		logger.info("User is not found, redirect to endpoint 'login'");
//		return "redirect:/login";
//	}

	@PostMapping ("signIn")
	public String openProfile(Authentication a, Model model) {
		logger.info("GET: endpoint 'profile'");
		User user = userService.findUserByEmail(a.getName());
		logger.info("User is found: " + user.toString());
		model.addAttribute("user", user);
		model.addAttribute("logs", logAuthService.findAllLogsByEmail(user.getEmail()));
		model.addAttribute("lstAvatar", storageService.loadAllAvatars(user));
		return "profile";
	}

	@GetMapping({ "profile", "signIn"})
	public String startSignUp(Model model) {
		logger.info("GET: endpoint 'signIn...'");
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		String username = a.getName();
		if (!username.equals("anonymousUser")) {
			logger.info("User has already authentified, redirect to endpoint 'profile'");
			User user = userService.findUserByEmail(username);
			model.addAttribute("user", user);
			model.addAttribute("logs", logAuthService.findAllLogsByEmail(user.getEmail()));
			model.addAttribute("lstAvatar", storageService.loadAllAvatars(user));
			return "profile";
		}
		return "login";
	}

	//fill registration form
	@PostMapping("signUp")
	public String addUser(@Valid @ModelAttribute User user,
						  BindingResult errors,
						  Model model,
						  HttpServletRequest request,
						  HttpServletResponse response) {
		logger.info("POST: endpoint 'signUp'");

		if(errors.hasErrors()) {
			errors.resolveMessageCodes("validation:");
			model.addAttribute("user", user);
			return "signUp_errors";
		}
		logger.info("User is authentified successfully");
		user.setRole(Role.USER);
		user.setProfileStatus(ProfileStatus.NOT_CONFIRMED);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		model.addAttribute("lstAvatar", storageService.loadAllAvatars(user));
		model.addAttribute("logs", logAuthService.findAllLogsByEmail(user.getEmail()));
		model.addAttribute("user", user);
		eventPublisher.publishEvent(new UserRegistrationEvent(user));
		return "redirect:/login";
	}

	@GetMapping("signUp")
	public String startSignIn(Model model) {
			logger.info("GET: endpoint 'signUp...'");
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		String username = a.getName();
		if (!username.equals("anonymousUser")) {
			User user = userService.findUserByEmail(username);
			model.addAttribute("user", user);
			model.addAttribute("lstAvatar", storageService.loadAllAvatars(user));
			return "redirect:/profile";
		}
		return "signUp";
	}

	@GetMapping("films")
	public String showFilms (Authentication a, Model model){
		String username = a.getName();
		if (username != null) {
			List<Film> films = filmService.findAll();
			model.addAttribute("films", films);
			model.addAttribute("user", userService.findUserByEmail(username));
			return "showfilmUsers";
		}
		return "redirect:/login";
	}

	@GetMapping("sessions")
	public String showSessions (Authentication a, Model model){
		String username = a.getName();
		if (username != null) {
			List<Session> sessions = sessionService.findAll();
			model.addAttribute("sessions", sessions);
			return "showsessionUsers";
		}
		return "redirect:/signIn";
	}

	@GetMapping("sessions/" + "{id}")
	public String showSessionById (Authentication a, @PathVariable Long id, Model model){
		String username = a.getName();
		if (username != null) {
			Optional<Session> session = sessionService.findById(id);
			model.addAttribute("session", session.get());
			User user = userService.findUserByEmail(username);
			model.addAttribute("user", user);
			return "sessiondetails";
		}
		return "redirect:/signIn";
	}

	@GetMapping("admin")
	public String startAdmin(Authentication a, Model model) {
		String username = a.getName();
		User user = userService.findUserByEmail(username);
		model.addAttribute("user", user);
		return "indexToAdmin";
	}

	@GetMapping("user")
	public String startUser(Authentication a, Model model) {
		String username = a.getName();
		User user = userService.findUserByEmail(username);
		model.addAttribute("user", user);
		if (user.getRole() == Role.ADMIN) {
			return "redirect:/admin";
		}
		return "indexToUser";
	}
}
