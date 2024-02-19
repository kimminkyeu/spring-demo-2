package spring.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.myapp.domain.Book;

// AuthorRepository 코드 참고
@Repository // NOTE: 강의에선 이 Annotation을 안붙히는데 괜찮을 까.
public interface BookRepository extends CrudRepository<Book, Long> {}
