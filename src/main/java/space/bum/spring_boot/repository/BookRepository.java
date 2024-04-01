package space.bum.spring_boot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import space.bum.spring_boot.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long>{ 
  List<Book> findByTitle(String title);
}
