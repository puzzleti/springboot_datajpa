package org.jdtbee.sb.sprjpa;

import java.time.LocalDate;
import org.jdtbee.sb.sprjpa.model.Member;
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
  CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
    return args -> {
      System.out.println("\nStart Run CommandLineRunner!\n");
      Member member1 = Member.builder().firstName("Thomi").lastName("Immen")
          .birthday(LocalDate.of(1968, 3, 1)).email("thomas@immen.de").build();
      memberRepository.save(member1);

      Member member2 = Member.builder().firstName("Ricola").lastName("Boetzi")
          .birthday(LocalDate.of(1975, 8, 12)).email("boetzi@swiss.ch").build();
      memberRepository.save(member2);

      Member member3 = Member.builder().firstName("Sahra").lastName("Maier")
          .birthday(LocalDate.of(1981, 2, 22)).email("smai@wib.com").build();
      memberRepository.save(member3);

      System.out.println("\nMemberList: " + memberRepository.findAll() + "\n");

      /* wegen der id ist das ein update! */
      Member member4 = Member.builder().id(1L).firstName("Thomas").lastName("Immen")
          .birthday(LocalDate.of(1968, 3, 1)).email("thomas@immen.de").build();
      memberRepository.save(member4);

      Member member5 = Member.builder().firstName("Birgit").lastName("Immen")
          .birthday(LocalDate.of(1972, 12, 19)).email("birgit@immen.de").build();
      memberRepository.save(member5);

      System.err.println("\nMemberList: " + memberRepository.findAll() + "\n");

      System.err.println("\nFind Member by Email: " + memberRepository.findByEmail("smai@wib.com"));
      System.err
          .println("\nFind Member by Lastname: " + memberRepository.findAllByLastName("Immen"));

      System.out.println("\\nEnd Run CommandLineRunner!\\n");

    };
  }

}
