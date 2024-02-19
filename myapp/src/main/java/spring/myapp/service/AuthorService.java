package spring.myapp.service;

import spring.myapp.domain.Author;

public interface AuthorService {
  Iterable<Author> findAll();
}
