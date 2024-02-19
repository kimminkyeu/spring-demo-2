package spring.myapp.service;

import org.springframework.stereotype.Service;
import spring.myapp.domain.Book;
import spring.myapp.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Iterable<Book> findAll() {
    return this.bookRepository.findAll();
  }
}
