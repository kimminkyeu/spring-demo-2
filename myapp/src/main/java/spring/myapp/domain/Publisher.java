package spring.myapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Publisher {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private @Getter @Setter Long id;

  private @Getter @Setter String publisherName;
  private @Getter @Setter String address;
  private @Getter @Setter String city;
  private @Getter @Setter String state;
  private @Getter @Setter String zipCode;

  @OneToMany(mappedBy = "publisher") // publisher will have many books
  private @Getter @Setter Set<Book> books;

  public Publisher() {
    System.out.println("Constructor - Publisher(void) called");
  }

  public Publisher(String publisherName, String address, String city, String state, String zipCode) {
    this.publisherName = publisherName;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.books = new HashSet<Book>();
    System.out.println("Constructor - Publisher(companyName) called");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Publisher publisher = (Publisher) o;

    return Objects.equals(id, publisher.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Publisher{" +
            "id=" + id +
            ", publisherName='" + publisherName + '\'' +
            ", address='" + address + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zipCode + '\'' +
            '}';
  }
}
