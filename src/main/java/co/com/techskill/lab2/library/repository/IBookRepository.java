package co.com.techskill.lab2.library.repository;

import co.com.techskill.lab2.library.domain.entity.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IBookRepository {
    Mono<Book> findByBookId(String bookId);
    Flux<Book> findAll();
    Mono<Book> findById(String id);
    Mono<Book>save(Book book);
}
