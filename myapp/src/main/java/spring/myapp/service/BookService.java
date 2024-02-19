package spring.myapp.service;

import spring.myapp.domain.Book;

public interface BookService {
  Iterable<Book> findAll();
}
