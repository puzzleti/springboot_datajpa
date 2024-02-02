package org.jdtbee.sb.sprjpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.jdtbee.sb.sprjpa.model.Address;
import org.jdtbee.sb.sprjpa.model.Author;
import org.jdtbee.sb.sprjpa.model.Book;
import org.jdtbee.sb.sprjpa.model.Member;
import org.jdtbee.sb.sprjpa.repositories.AddressRepository;
import org.jdtbee.sb.sprjpa.repositories.BookRepository;
import org.jdtbee.sb.sprjpa.repositories.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringbootDatajpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootDatajpaApplication.class, args);
  }


  @Bean
  CommandLineRunner commandLineRunner(MemberRepository memberRepository,
      AddressRepository addressRepository, BookRepository bookRepository) {
    return args -> {
      System.err.println("\n\nStart Run CommandLineRunner!\n\n");

      Address address = Address.builder().street("Pestalozzistrasse").number("37d").zipCode("79540")
          .city("Loerrach").phoneNumber("123456789").build();

      Member member = Member.builder().firstName("Thomas").lastName("Immen").email("test@gmail.com")
          .birthday(LocalDate.of(1968, 3, 1)).address(address).build();

      Book book1 =
          Book.builder().bookName("Spring Boot in Action").createdAt(LocalDateTime.now()).build();
      Book book2 =
          Book.builder().bookName("Mastering Spring Boot").createdAt(LocalDateTime.now()).build();
      Book book3 =
          Book.builder().bookName("Learning Spring Boot").createdAt(LocalDateTime.now()).build();

      Author author1 = Author.builder().authorName("Hugo Meier").build();
      Author author2 = Author.builder().authorName("Berrit Gressler").build();
      Author author3 = Author.builder().authorName("Bill Moin").build();
      Author author4 = Author.builder().authorName("Gisell Williams").build();

      book1.addAuthor(author1);
      book1.addAuthor(author2);
      book2.addAuthor(author3);
      book3.addAuthor(author4);

      member.addBook(book1);
      member.addBook(book2);
      member.addBook(book3);

      memberRepository.save(member);

      memberRepository.findById(1L).ifPresent(return_member -> {
        List<Book> books = bookRepository.findAllByMemberId(return_member.getId());
        System.err.println(return_member);
        books.forEach(book -> {
          System.out.println("\t" + book);
          book.getAuthors().forEach(author -> {
            System.err.println("\t\t" + author);
          });
        });
      });


      System.err.println("\n\nEND Run CommandLineRunner!\n\n");
    };
  }

}
