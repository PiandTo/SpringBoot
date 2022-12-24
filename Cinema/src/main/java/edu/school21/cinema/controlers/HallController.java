package edu.school21.cinema.controlers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.HallService;
import edu.school21.cinema.services.UserService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/admin/panel/")
@Controller
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private UserService userService;

    public HallController() {}

    @GetMapping("halls/new")
    public String addHalls (Authentication a, Model model) {
        User user = userService.findUserByEmail(a.getName());
        model.addAttribute("user", user);
        return "halls";
    }

    @PostMapping("hall")
    public String addHalls(@Valid Hall hall, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            model.addAttribute("noErrors", true);
            hallService.save(hall);
            return "redirect:/admin/panel/halls";
        } else {
            Authentication a = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(a.getName());
            model.addAttribute("user", user);
            model.addAttribute("hall", hall);
            return "halls";
        }
    }

    @GetMapping("halls")
    public String showHalls (Model model){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(a.getName());
        List<Hall> halls= hallService.findAll();
        model.addAttribute("halls", halls);
        model.addAttribute("user", user);
        return "showhall";
    }


    @GetMapping("halls/update/{id}")
    public String updateHall(Authentication a, @PathVariable("id") Long id, Model model) {
        User user = userService.findUserByEmail(a.getName());
        Hall hall = hallService.findById(id);
        model.addAttribute("hall", hall);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "updateHall";
    }

    @PostMapping("halls/update/{id}")
    public String updateHall (@Valid Hall hall, BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        System.out.println("====" + hall);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("noError", true);
            Hall tempHall = hallService.findById(id);
            tempHall.setName(hall.getName());
            tempHall.setNumber(hall.getNumber());
            tempHall.setAvailableSeats(hall.getAvailableSeats());
            hallService.update(tempHall);
            System.out.println("====" + tempHall);
            return "redirect:/admin/panel/halls";
        } else {
            model.addAttribute("hall", hall);
            return "updateHall";
        }
    }

    @GetMapping("halls/delete/{id}")
    public String deleteHall(@PathVariable(value = "id") Long id){
        Hall hall = hallService.findById(id);
        hallService.delete(hall);
        return "redirect:/admin/panel/halls";
    }

}