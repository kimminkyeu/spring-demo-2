package spring.myapp.service;

import org.springframework.stereotype.Service;
import spring.myapp.domain.Author;
import spring.myapp.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository authorRepository;

  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public Iterable<Author> findAll() {
    return this.authorRepository.findAll();
  }
}
