package org.jdtbee.sb.sprjpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.jdtbee.sb.sprjpa.model.Address;
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

  // @Bean
  // CommandLineRunner commandLineRunner(MemberRepository memberRepository,
  // AddressRepository addressRepository, BookRepository bookRepository) {
  // return args -> {
  // System.out.println("\nStart Run CommandLineRunner!\n");
  //
  // System.out.println("\ncreate the first address!\n");
  // Address address1 = Address.builder().street("Weitweg").number("37d").zipCode("79540")
  // .city("Loerrach").phoneNumber("017655251633").build();
  // Member member1 = Member.builder().firstName("Thomi").lastName("Immen")
  // .birthday(LocalDate.of(1968, 3, 1)).email("thomas@immen.de").address(address1).build();
  // Book book1 = Book.builder().bookName("Frische Luft am Feldberg")
  // .createdAt(LocalDateTime.now()).build();
  // member1.addBook(book1);
  // member1.addBook(Book.builder().bookName("Wandere mein Freund")
  // .createdAt(LocalDateTime.of(1999, 11, 23, 12, 30)).build());
  // member1.addBook(Book.builder().bookName("Gold glänzt nicht alles")
  // .createdAt(LocalDateTime.of(1922, 8, 8, 17, 45)).build());
  // memberRepository.save(member1);
  //
  // System.out.println("\ncreate the second address!\n");
  // Address address2 = Address.builder().street("Rheinufer").number("10K").zipCode("12345")
  // .city("Basel").phoneNumber("015598562233").build();
  // Member member2 = Member.builder().firstName("Ricola").lastName("Boetzi")
  // .birthday(LocalDate.of(1975, 8, 12)).email("boetzi@swiss.ch").address(address2).build();
  // memberRepository.save(member2);
  //
  // Address address3 = Address.builder().street("Hauptstr.").number("22").zipCode("77234")
  // .city("Lorsch").phoneNumber("01778192534").build();
  // Member member3 = Member.builder().firstName("Sahra").lastName("Maier")
  // .birthday(LocalDate.of(1981, 2, 22)).email("smai@wib.com").address(address3).build();
  // member3.addBook(Book.builder().bookName("Wandere mein Freund")
  // .createdAt(LocalDateTime.of(1999, 11, 23, 12, 30)).build());
  // member3.addBook(Book.builder().bookName("Tue Ruhe")
  // .createdAt(LocalDateTime.of(1961, 3, 2, 9, 10)).build());
  // memberRepository.save(member3);
  //
  // System.out.println("\nMemberList: " + memberRepository.findAll() + "\n");
  //
  // System.out.println("\nBookList: " + bookRepository.findAll() + "\n");
  //
  // System.out
  // .println("\nBooks from Member No.2: " + bookRepository.findAllByMemberId(1L) + "\n");
  //
  // /* wegen der id ist das ein update! */
  // Member member4 = Member.builder().id(1L).firstName("Thomas").lastName("Immen")
  // .birthday(LocalDate.of(1968, 3, 1)).email("thomas@immen.de").address(address1).build();
  // memberRepository.save(member4);
  //
  // Address address5 = Address.builder().street("Kirchenstrasse").number("31").zipCode("89376")
  // .city("Muenchen").phoneNumber("017612345678").build();
  // Member member5 = Member.builder().firstName("Birgit").lastName("Immen")
  // .birthday(LocalDate.of(1972, 12, 19)).email("birgit@immen.de").address(address5).build();
  // memberRepository.save(member5);
  //
  // System.err.println("\nMemberList: " + memberRepository.findAll() + "\n");
  //
  // System.err.println("\nFind Member by Email: " + memberRepository.findByEmail("smai@wib.com"));
  // System.err
  // .println("\nFind Member by Lastname: " + memberRepository.findAllByLastName("Immen"));
  //
  // addressRepository.findAll().forEach(System.out::println);
  //
  // addressRepository.findById(2L).ifPresent(System.out::println);
  //
  //
  // System.out.println("\\nEnd Run CommandLineRunner!\\n");
  //
  // };
  // }

  @Bean
  CommandLineRunner commandLineRunner(MemberRepository memberRepository,
      AddressRepository addressRepository, BookRepository bookRepository) {
    return args -> {
      System.err.println("\n\nStart Run CommandLineRunner!\n\n");
      Address address = Address.builder().street("PLozziStr").number("37d").zipCode("9000")
          .city("Lörrach").phoneNumber("123456789").build();
      Member member = Member.builder().firstName("Thomas").lastName("Immen").email("test@web.com")
          .birthday(LocalDate.of(1988, 7, 1)).address(address).build();
      Book book =
          Book.builder().bookName("Spring Boot in Action").createdAt(LocalDateTime.now()).build();
      member.addBook(book);
      member.addBook(
          Book.builder().bookName("Mastering Spring Boot").createdAt(LocalDateTime.now()).build());
      member.addBook(
          Book.builder().bookName("Learning Spring Boot").createdAt(LocalDateTime.now()).build());
      memberRepository.save(member);

      System.err.println(
          "\nSave Member & Books =============================================================\n");

      memberRepository.findById(1L).ifPresent(ret -> {
        List<Book> books = bookRepository.findAllByMemberId(ret.getId());
        System.err.print("\n\n" + ret.getId() + " " + ret.getFirstName() + " " + ret.getLastName()
            + " " + ret.getEmail());
        System.err.println(" - " + ret.getAddress().getId() + " " + ret.getAddress().getStreet()
            + " " + ret.getAddress().getNumber() + " " + ret.getAddress().getZipCode() + " "
            + ret.getAddress().getCity() + " " + ret.getAddress().getPhoneNumber());
        books.forEach(ret_book -> System.err.println("\t" + ret_book.getId() + " "
            + ret_book.getBookName() + " " + ret_book.getCreatedAt()));
      });

      System.err.println("\n=============================================================\n");

      bookRepository.findAll().forEach(ret -> {
        Member members = memberRepository.findById(ret.getMember().getId()).get();
        System.err.println(ret.getId() + " " + ret.getBookName());
        System.err.println("\t" + members.getId() + " - " + members.getFirstName() + " "
            + members.getLastName() + "\n\n");



      });



      System.err.println("\n\nEND Run CommandLineRunner!\n\n");
    };
  }

}
