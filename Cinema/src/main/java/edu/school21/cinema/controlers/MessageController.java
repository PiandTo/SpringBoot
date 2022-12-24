package edu.school21.cinema.controlers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.MessageService;
import edu.school21.cinema.services.UserService;
import freemarker.core.NonNodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.message.config.AuthConfig;
import java.util.Objects;

@Controller
public class MessageController {
    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/ws/{id}")
    public void response(@DestinationVariable("id") Long id, Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        Thread.sleep(1000); // simulated delay
        Film film = filmService.findById(id);
        message.setType(Message.MessageType.CHAT);
        message.setFilm(film);
        try {
            Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", message.getUsername());
            Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("id", id);
        } catch (NullPointerException e) {
            e.getMessage();
        }
        messageService.save(message);
        template.convertAndSend("/topic/messages/" + id, message);
    }

    @RequestMapping(value = {"/admin/panel/films/{id}/chat", "/films/{id}/chat"}, produces = "text/plain;charset=UTF-8")
    public String addUser(Authentication a,
                          @PathVariable Long id,
                          Model model) {
        Film film = filmService.findById(id);
        User user = userService.findUserByEmail(a.getName());
        model.addAttribute("film", film);
        model.addAttribute("user", user);
        Message msg = new Message();
        msg.setType(Message.MessageType.JOIN);
        msg.setFilm(film);
        msg.setUsername(user.getFirstName());
        template.convertAndSend("/topic/messages/" + id, msg);
        model.addAttribute("messages", messageService.findAllById(film.getId()));
        return "chat";
    }
}
