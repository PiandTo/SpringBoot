package edu.school21.cinema.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class IndexController {

    public IndexController() {

    }
    @GetMapping("/")
    public String showIndexPage (){
        return "index";
    }

}