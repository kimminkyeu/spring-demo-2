package spring.myapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
//@SequenceGenerator(name = "my_sequence", sequenceName = "my_sequence", initialValue = 1)
public class Author {
  // Member data -------------------------------------------------------
  @Id
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
  @GeneratedValue(strategy = GenerationType.AUTO) // 어차피 sequence는 만들어진다. 기본 시퀀스로...
  private @Getter @Setter Long id;

  private @Getter @Setter String firstName;

  private @Getter @Setter String lastName;

//  @ManyToMany(mappedBy = "author", cascade = CascadeType.ALL)
  @ManyToMany(mappedBy = "authors")
  private @Getter @Setter Set<Book> books;

  // Constructors -------------------------------------------------------
  public Author() {
    System.out.println("Constructor - Author(void) called");
    // id, firstName, Lastname 모두 null.
  }
  public Author(String firstName, String lastName) {
    System.out.println("Constructor - Author(firstName, lastName) called");
    this.firstName = firstName;
    this.lastName = lastName;
    this.books = new HashSet<Book>();
  }

  // Object methods -------------------------------------------------------
  @Override
  public String toString() {
    return "Author{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", books=" + books +
            '}';
  }

  // NOTE: Hybernate가 같은 오브젝트인지 확인할 방법을 제공 ( id )
  @Override // just like Cpp operator overloading
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Author author = (Author) o;

    return Objects.equals(id, author.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
