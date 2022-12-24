package edu.school21.cinema.filters;

import com.sun.codemodel.internal.JForEach;
import edu.school21.cinema.models.Logs;
import edu.school21.cinema.services.LogAuthService;
import edu.school21.cinema.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	@Autowired
	private LogAuthService logAuthService;

	private static final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) throws IOException, IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		Logs log = new Logs();
		LocalTime now = LocalTime.now();
		LocalDate now1 = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String data = "" + now1.getMonth() + " " + now1.getDayOfMonth() + ", " + now1.getYear() + "";
		log.setTime(now.format(formatter));
		log.setDate(data);
		log.setEmail(authentication.getName());
		log.setIp(request.getRemoteAddr());
		logAuthService.save(log);
//		String url = re
		this.setAlwaysUseDefaultTargetUrl(true);
		if (roles.toArray()[0].equals("ROLE_USER")) {
			this.setDefaultTargetUrl("/user");
			logger.info("CustomSuccessHandler: user " + authentication.getName() + " is authenticated");
		} else {
			this.setDefaultTargetUrl("/admin");
			logger.info("CustomSuccessHandler: admin " + authentication.getName() + " is authenticated");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
