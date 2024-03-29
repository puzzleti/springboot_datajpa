package org.jdtbee.sb.sprjpa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"member", "authors"})
@Entity(name = "book")
@Table(name = "book")
public class Book {

  @Id
  @SequenceGenerator(name = "book_squence", sequenceName = "book_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "book_name", nullable = false, columnDefinition = "TEXT")
  private String bookName;

  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false, referencedColumnName = "id",
      foreignKey = @ForeignKey(name = "member_book_fk"))
  private Member member;

  // @Override
  // public String toString() {
  // StringBuilder representationOfBook = new StringBuilder(
  // "Book [id=" + id + ", bookName=" + bookName + ", createdAt=" + createdAt + "]");
  // if (this.member != null) {
  // representationOfBook.append(" Member:" + member.toString());
  // }
  // return representationOfBook.toString();
  // }


  @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Builder.Default
  private List<Author> authors = new ArrayList<>();


  public void addAuthor(Author author) {
    authors.add(author);
    author.getBooks().add(this);
  }

  public void removeAuthor(Author author) {
    authors.remove(author);
    author.getBooks().remove(this);
  }

}
