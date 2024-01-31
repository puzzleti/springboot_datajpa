package org.jdtbee.sb.sprjpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Member")
@Table(name = "member",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "member_email_unique")})
public class Member {

  @Id
  @SequenceGenerator(name = "member_sequence", sequenceName = "member_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence")
  @Column(name = "id", updatable = false)
  private Long id;
  @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
  private String firstName;
  @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
  private String lastName;
  @Column(name = "email", nullable = false, columnDefinition = "TEXT")
  private String email;
  @Column(name = "birthday", nullable = false, columnDefinition = "DATE")
  private LocalDate birthday;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL,
      fetch = FetchType.LAZY)
  @Builder.Default
  private List<Book> books = new ArrayList<>();

  public void addBook(Book book) {
    if (!this.books.contains(book) || this.books.isEmpty()) {
      this.books.add(book);
      book.setMember(this);
    }
  }

  public void removeBook(Book book) {
    if (this.books.contains(book)) {
      this.books.remove(book);
      book.setMember(null);
    }
  }

  @Override
  public String toString() {
    return "Member [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
        + email + ", birthday=" + birthday + ", address=(" + this.address.getCity() + ", "
        + this.address.getZipCode() + ", " + this.address.getStreet() + ", "
        + this.address.getNumber() + ") " + "]";
  }

}
