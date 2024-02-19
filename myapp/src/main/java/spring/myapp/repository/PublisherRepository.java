package spring.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.myapp.domain.Publisher;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {}
