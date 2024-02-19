package spring.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.myapp.service.AuthorService;

@Controller
public class AuthorController {
  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @RequestMapping(path = "/authors", method = RequestMethod.GET)
  public String getAuthors(Model model) {
    model.addAttribute("authors", this.authorService.findAll());
    return "authorList";
  }
}
