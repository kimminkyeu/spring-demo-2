package spring.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.myapp.domain.Author;

// NOTE: 왜 Repository는 Interface인가?
//       1. 데이터 멤버에 따라 getXXXbyXX를 직접 생성해주기 위함???

// NOTE: @Repository를 붙히지 않아도 된다. 왜???
//       https://skatpdnjs.tistory.com/66 --> 아직 이해 안감... ㅠ
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {}
