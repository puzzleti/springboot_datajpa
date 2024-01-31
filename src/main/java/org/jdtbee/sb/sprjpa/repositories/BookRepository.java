package org.jdtbee.sb.sprjpa.repositories;

import java.util.List;
import org.jdtbee.sb.sprjpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByMemberId(Long id);

}
