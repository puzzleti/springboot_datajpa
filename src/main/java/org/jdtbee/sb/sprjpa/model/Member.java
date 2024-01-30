package org.jdtbee.sb.sprjpa.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


}
