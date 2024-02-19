package spring.myapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
public class Book {
  // Member data -------------------------------------------------------
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private @Getter @Setter Long id;
  private @Getter @Setter String title;
  private @Getter @Setter String isbn;

  @ManyToMany // NOTE: we will have many authors to many books
  @JoinTable( name = "author_book"
              , joinColumns = @JoinColumn(name = "book_id")
              , inverseJoinColumns = @JoinColumn(name = "author_id") )
  private @Getter @Setter Set<Author> authors;

  @ManyToOne // NOTE: Book will have only one publisher
  private @Getter @Setter Publisher publisher;

  /**
   * NOTE: What is the purpose of joinTable?
   * This tells Hibernate that the Book is the owner side and the author
   * is the being owned side of the many-to-many relationship.
   * In the DB, we have a book table, an "author_book" table and an author table.
   * The author_book table shows the relationship between book and author.
   * But in order to define the book and author relationship is bidirectional,
   * we need to specify which one is the owner and which is the being owned.
   * (1) The class with @JoinTable is usually the owner entity.
   * (2) The class with (= mappedBy) is usually the being owned.
   * ------------------------------------------------------------------------
   * NOTE: In DB, Why is the Book an owner of the Author?
   * You can swap the owner side and being owned side and see. It is more like a design issue.
   * We can have an author without a book. But it won't make sense to have a book without author.
   * So, in the join table, it won't make sense to have a book ID , but leaving the author id as null.
   */

  // Constructors -------------------------------------------------------
  public Book() {
    System.out.println("Constructor - Book(void) called");
  }
  public Book(String title, String isbn) {
    System.out.println("Constructor - Book(title, isbn) called");
    this.title = title;
    this.isbn = isbn;
    this.publisher = null;
  }

  // Object methods -------------------------------------------------------
  @Override
  public String toString() {
    return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", isbn='" + isbn + '\'' +
            ", authors=" + authors +
            '}';
  }

  // NOTE: Hybernate가 같은 오브젝트인지 확인할 방법을 제공 ( id )
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    return Objects.equals(id, book.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
