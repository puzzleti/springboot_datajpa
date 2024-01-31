package org.jdtbee.sb.sprjpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "address")
@Table(name = "member_address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  @Id
  @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
  @Column(name = "id", updatable = false, unique = true)
  private Long id;

  @Column(name = "street", nullable = false, columnDefinition = "TEXT")
  private String street;
  @Column(name = "number", nullable = false, columnDefinition = "TEXT")
  private String number;
  @Column(name = "zip_code", nullable = false, columnDefinition = "TEXT")
  private String zipCode;
  @Column(name = "city", nullable = false, columnDefinition = "TEXT")
  private String city;
  @Column(name = "phone_number", nullable = false, columnDefinition = "TEXT")
  private String phoneNumber;

  @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
  private Member member;

  @Override
  public String toString() {
    return "Address [id=" + id + ", street=" + street + ", number=" + number + ", zipCode="
        + zipCode + ", city=" + city + ", phoneNumber=" + phoneNumber + "]";
  }



}
