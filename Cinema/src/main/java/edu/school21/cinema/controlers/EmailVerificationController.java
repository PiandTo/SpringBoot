package edu.school21.cinema.controlers;

import edu.school21.cinema.models.ProfileStatus;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.EmailVerificationService;
import edu.school21.cinema.services.UserService;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EmailVerificationController {
	@Autowired
	EmailVerificationService emailVerificationService;

	@Autowired
	UserService userService;

	@RequestMapping("confirm/{uuid}")
	public String mailregistration(@PathVariable String uuid) {
		byte[] verificationId = Base64.getDecoder().decode(uuid);
		String username = emailVerificationService.getUsernameByVerificationId(new String(verificationId));
		if (username != null) {
			User user = userService.findUserByEmail(username);
			user.setProfileStatus(ProfileStatus.CONFIRMED);
			userService.update(user);
			return "redirect:/user";
		}
		return "redirect:/login";
	}
}
