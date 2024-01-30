package org.jdtbee.sb.sprjpa.repositories;

import java.util.List;
import java.util.Optional;
import org.jdtbee.sb.sprjpa.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);

  Optional<List<Member>> findAllByLastName(String lastname);

}
