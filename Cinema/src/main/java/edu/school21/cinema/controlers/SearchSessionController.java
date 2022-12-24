package edu.school21.cinema.controlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.SessionSearch;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.SessionService;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class SearchSessionController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @GetMapping("/sessions/search")
    public @ResponseBody
    String searchSession(@RequestParam(value = "filmName", required = false) String searchInput) throws JsonProcessingException {
        if (searchInput == null) {
            return null;
        }
        List<Session> sessions = sessionService.findByFilmName(searchInput);

        Set<SessionSearch> sessionSearchList = new HashSet<>();
        for (Session session : sessions) {
            sessionSearchList.add(new SessionSearch(session));
        }
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule=new JavaTimeModule();
        mapper.registerModule(javaTimeModule);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(sessionSearchList);
    }

    @GetMapping("sessions/search/")
    public String showSessions (Authentication a, Model model){
        User user = userService.findUserByEmail(a.getName());
        if (user == null) {
            return "redirect:/signIn";
        }
        List<Session> sessions= sessionService.findAll();
        model.addAttribute("sessions", sessions);
        model.addAttribute("user", user);
        return "showsessionsearch";
    }
}
