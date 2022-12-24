package edu.school21.cinema.controlers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.HallService;
import edu.school21.cinema.services.SessionService;
import edu.school21.cinema.services.UserService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/admin/panel/")
    @Controller
    public class SessionController {
        @Autowired
        private SessionService sessionService;
        @Autowired
        private FilmService filmService;
        @Autowired
        private HallService hallService;
        @Autowired
        private UserService userService;

        public SessionController() {
        }

        @GetMapping("sessions/update/{id}")
        public String updateFilm(@PathVariable("id") Long id, Authentication a, Model model) {
            Optional<Session> session = sessionService.findById(id);
            List<Hall> halls= hallService.findAll();
            List<Film> films = filmService.findAll();
            User user = userService.findUserByEmail(a.getName());
            model.addAttribute("session", session.get());
            model.addAttribute("id", id);
            model.addAttribute("halls", halls);
            model.addAttribute("films", films);
            model.addAttribute("user", user);
            return "updateSession";
        }

        @PostMapping("sessions/update/{id}")
        public String updateHall (@Valid Session session, BindingResult bindingResult, Model model,
                                  @PathVariable("id") Long id) {
            System.out.println("====" + session);
            List<Session> sessions= sessionService.findAll();
            model.addAttribute("sessions", sessions);
            if (!bindingResult.hasErrors()) {
                model.addAttribute("noError", true);

                Optional<Session> tempSession = sessionService.findById(id);
                tempSession.get().setHall(hallService.findById(session.getHall().getHallId()));
                tempSession.get().setFilm(filmService.findById(session.getFilm().getId()));
                tempSession.get().setDateTime(session.getDateTime());
                tempSession.get().setTicketCost(session.getTicketCost());

                sessionService.update(tempSession.get());
                System.out.println("====" + tempSession);
                return "redirect:/admin/panel/sessions";
            } else {
                model.addAttribute("session", session);
                return "updateSession";
            }
        }

        @GetMapping("sessions")
        public String showSessions (Authentication a, Model model){
            String username = a.getName();
            User user = userService.findUserByEmail(username);
            List<Session> sessions= sessionService.findAll();
            model.addAttribute("sessions", sessions);
            model.addAttribute("user", user);
            return "showsession";
        }

        @GetMapping("sessions/" + "{id}")
        public String showSessionById (Authentication a, @PathVariable Long id, Model model){
            String username = a.getName();
            User user = userService.findUserByEmail(username);
            Optional<Session> session = sessionService.findById(id);
            model.addAttribute("user", user);
            model.addAttribute("session", session.get());
            return "sessiondetails";
        }
        
        @GetMapping("sessions/new")
        public String addSessions (Authentication a, Model model) {
            User user = userService.findUserByEmail(a.getName());
            List<Hall> halls= hallService.findAll();
            List<Film> films = filmService.findAll();
            model.addAttribute("halls", halls);
            model.addAttribute("films", films);
            model.addAttribute("user", user);
            return "sessions";
        }

        @PostMapping("session")
        public String addSessions(
                                  @ModelAttribute("dateTime") String dateInString,
                                  @ModelAttribute("ticketCost") float cost,
                                  @ModelAttribute("film.id") Long movie_id,
                                  @ModelAttribute("hall.hallId") Long hall_id,
                                  BindingResult result
                                  ) {
            if (!result.hasErrors()) {
                Session session = new Session();
                session.setId(0L);
                session.setDateTime(LocalDateTime.parse(dateInString));
                session.setFilm(filmService.findById(movie_id));
                session.setHall(hallService.findById(hall_id));
                session.setTicketCost(BigDecimal.valueOf(cost));
                sessionService.save(session);
                return "redirect:/admin/panel/sessions";
            }
            return "sessions";
        }

        @GetMapping("sessions/delete/{id}")
        public String deleteSession(@PathVariable(value = "id") Long id){
            Optional<Session> session = sessionService.findById(id);
            System.out.println("====" + session);
            sessionService.delete(session.get());
            return "redirect:/admin/panel/sessions";
        }
    }
