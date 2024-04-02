package space.bum.spring_boot.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/index")
  public String indexPage(Model model) {
    model.addAttribute("date", LocalDate.now());
    return "index";
  }
}
