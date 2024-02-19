package spring.myapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.myapp.domain.Author;
import spring.myapp.domain.Book;
import spring.myapp.domain.Publisher;
import spring.myapp.repository.AuthorRepository;
import spring.myapp.repository.BookRepository;
import spring.myapp.repository.PublisherRepository;

/* NOTE: Create custom bean with @Component annotation
* @Component is an annotation that allows Spring to detect our custom beans automatically.
* In other words, without having to write any explicit code, Spring will:
*   - (1) Scan our application for classes annotated with @Component.
*   - (2) Instantiate them and inject any specified dependencies into them.
*   - (3) Inject them wherever needed.
* */
@Component
public class BootstrapData implements CommandLineRunner {
  // NOTE: 이 방식은, @Configuration bean을 사용하지 않고, Custom bean을 구현하는 방식이다.
  // : 아래 방식과 차이점은 무엇일까?
  //   https://github.com/kimminkyeu/spring-demo/blob/master/src/main/java/com/example/demo/config/StudentConfig.java

  // NOTE: Constructor Dependency injection
  // : on Startup, spring will automatically autowire in the implementations
  //   of the repositories that are being provided for us by spring data JPA.
  //   https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired.html
  //   --> 이미 메모리에 올라간 AuthorRepository instance와 BookRepository instance를 Spring이 자동 연결.
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;
  @Autowired
  public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
//    DatabaseWizard.exportDDL_V1();
    // NOTE: Why use returned instance after save() on Spring Data JPA Repository?
    //       --  이 부분을 명확히 이해하려면, JPA가 작동하는 방식, DB를 연결하는 방식을 이해해야 한다.
    //       --  java instance가 db와 mapping된다면, instance의 멤버 데이터를 바꾸면 db도 바뀌는가?
    //       --  ** 이 부분은 JPA의 동작 원리를 이해해야 설명 가능할 것이다.. **
    // : if the entity isn't new, merge is called.
    //   merge copies the state of its argument into the attached entity with
    //   the same ID, and returns the attached entity
    System.out.println("***** (1-1) *****");
    Author eric = new Author("Eric", "Evans");
    Author rod = new Author("Rod", "Johnson");
    Book ddd = new Book("Domain Driven Design", "123456");
    Book noEJB = new Book("J2EE Development without EJB", "54757585");
    Publisher publisher = new Publisher("pubName" ,"addr", "city", "state", "zip");
    System.out.println("***** (1-2) *****");
    eric = authorRepository.save(eric);
    rod = authorRepository.save(rod);
    ddd = bookRepository.save(ddd);
    noEJB = bookRepository.save(noEJB);
    publisher = publisherRepository.save(publisher);

    System.out.println("***** (2-1) *****");
    ddd.setPublisher(publisher); // 책 인스턴스 정보 갱신
    noEJB.setPublisher(publisher);
    System.out.println("***** (2-2) *****");
    ddd = bookRepository.save(ddd); // 갱신한 인스턴스를 DB에 반영
    noEJB = bookRepository.save(noEJB);

    System.out.println("***** (3-1) *****");
    eric.getBooks().add(ddd); // 인스턴스 정보 갱신
    rod.getBooks().add(noEJB);
    System.out.println("***** (3-2) *****");
    eric = authorRepository.save(eric); // 갱신한 인스턴스를 DB에 반영
    rod = authorRepository.save(rod);

    // NOTE: 추가 의문점. jpa 인스턴스 Author, Book은 모두 null임.
    //       지금 Author에서만 set<Book>을 특정 생성자에서 메모리에 잡아주는데
    //       (1) Book에서는 안해주고 있는 이유...?
    //       (2) Author의 기본 생성자에서 set<Book> 메모리에 안잡아도 되나?
    //       (3) 왜 save를 2번이나 하지...?
    //       (4) GeneratedValue 를 Sequence로 하면 어떻게 되는지 직접 태스트


    System.out.println(" --------------- In Bootstrap ------------------");
    System.out.println("Author Count:" + authorRepository.count());
    System.out.println("Book Count:" + bookRepository.count());
    System.out.println("Publisher Count:" + publisherRepository.count());
  }
}