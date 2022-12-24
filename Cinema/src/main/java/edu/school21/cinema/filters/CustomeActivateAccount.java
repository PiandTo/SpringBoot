package edu.school21.cinema.filters;

import edu.school21.cinema.models.ProfileStatus;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.EmailVerificationService;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

@Component
public class CustomeActivateAccount implements Filter {

	@Autowired
	private EmailVerificationService emailVerificationService;

	@Autowired
	private UserService userService;


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		String url = null;
		if (servletRequest instanceof HttpServletRequest) {
			url = ((HttpServletRequest)servletRequest).getRequestURL().toString();
		}
		assert url != null;
		String[] uuid = url.split("/confirm/");
		if (uuid.length == 2) {
			System.out.println(uuid[1]);
			byte[] verificationId = Base64.getDecoder().decode(uuid[1]);
			String username = emailVerificationService.getUsernameByVerificationId(new String(verificationId));
			if (username != null) {
				User user = userService.findUserByEmail(username);
				user.setNonEnable(true);
				userService.update(user);
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
