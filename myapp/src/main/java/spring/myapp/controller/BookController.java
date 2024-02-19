package spring.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.myapp.service.BookService;

@Controller
public class BookController {
  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @RequestMapping(value = "/books", method = RequestMethod.GET)
  public String getBooks(Model model) {
    model.addAttribute("books", bookService.findAll()); // attr은 thymeleaf에서 접근하는 변수.
    return "bookList"; // return 하는 string이 곧 html의 파일명. (books.html)
  }
}
