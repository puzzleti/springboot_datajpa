package org.jdtbee.sb.sprjpa.model;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@ToString(exclude = "books")
@Entity(name = "author")
@Table(name = "autor")
public class Author {

  @Id
  @SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "author_name", nullable = false, columnDefinition = "TEXT")
  private String authorName;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "book_author",
      joinColumns = @JoinColumn(name = "author_id",
          foreignKey = @ForeignKey(name = "book_author_author_id_fk")),
      inverseJoinColumns = @JoinColumn(name = "book_id",
          foreignKey = @ForeignKey(name = "book_author_book_id_fk")))
  @Builder.Default
  private List<Book> books = new ArrayList<>();

}
