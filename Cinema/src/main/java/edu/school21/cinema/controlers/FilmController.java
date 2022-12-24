package edu.school21.cinema.controlers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/admin/panel/")
@Controller
public class FilmController {
    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    public FilmController() {}

    @GetMapping("films")
    public String showFilms (Authentication authentication, Model model){
        List<Film> films= filmService.findAll();
        User user = userService.findUserByEmail(authentication.getName());
        model.addAttribute("films", films);
        model.addAttribute("user", user);
        return "showfilm";
    }

    @GetMapping("films/new")
    public String addFilms (Authentication a, Model model) {
        User user = userService.findUserByEmail(a.getName());
        model.addAttribute("user", user);
        return "films";
    }


    @GetMapping("films/delete/{id}")
    public String deleteFilm(@PathVariable Long id) {
        Film film = filmService.findById(id);
        filmService.delete(film);
        return "redirect:/admin/panel/films";
    }

    @PostMapping("film")
    public String addFilms(@Valid Film film, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            model.addAttribute("noErrors", true);
            if (film.getPoster() == null)
                film.setPoster("films.jpeg");
            filmService.save(film);
            return "redirect:/admin/panel/films";
        }
        model.addAttribute("film", film);
        return "films";
    }

    @GetMapping("films/update/{id}")
    public String updateFilm(Authentication a, @PathVariable("id") Long id, Model model) {
        User user = userService.findUserByEmail(a.getName());
        Film film = filmService.findById(id);
        model.addAttribute("film", film);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "updateFilm";
    }

    @PostMapping("films/update/{id}")
    public String updateHall (@Valid Film film, BindingResult bindingResult, Model model,
                              @PathVariable("id") Long id) {
        System.out.println("====" + film);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("noError", true);

            Film tempFilm = filmService.findById(id);
            tempFilm.setTitle(film.getTitle());
            tempFilm.setDescription(film.getDescription());
            tempFilm.setYearRelease(film.getYearRelease());
            tempFilm.setAgeRestriction(film.getAgeRestriction());

            filmService.update(tempFilm);
            System.out.println("====" + tempFilm);
            return "redirect:/admin/panel/films";
        } else {
            model.addAttribute("film", film);
            return "updateFilm";
        }
    }
}
