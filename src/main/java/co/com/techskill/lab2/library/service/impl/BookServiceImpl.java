package co.com.techskill.lab2.library.service.impl;

import co.com.techskill.lab2.library.config.BookMapper;
import co.com.techskill.lab2.library.config.BookMapperImpl;
import co.com.techskill.lab2.library.domain.dto.BookDTO;
import co.com.techskill.lab2.library.repository.IBookRepository;
import co.com.techskill.lab2.library.service.IBookService;
import co.com.techskill.lab2.library.service.dummy.BookService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class BookServiceImpl implements IBookService {
    private final BookService bookRepository;
    private final BookMapper bookMapper;


    public BookServiceImpl(BookService bookRepository){
        this.bookRepository = bookRepository;
        this.bookMapper = new BookMapperImpl();

    }

    /*Operadores fundamentales
    * Transformación: .map y .flatMap
    * Filtrado: .filter
    * Combinación: .zip .merge .concat */
    @Override
    public Flux<BookDTO> findAll() {
        return bookRepository.dummyFindAll();
    }

    @Override
    public Mono<BookDTO> findById(String id) {
        return bookRepository.dummyFindById(id);
    }

    @Override
    public Mono<BookDTO> save(BookDTO bookDTO) {
        bookDTO.setBookId(UUID.randomUUID().toString().substring(0,10));
        bookDTO.setAvailable(true);
        return bookRepository
                .save(bookMapper.toEntity(bookDTO))
                .map(book -> bookMapper.toDTO(book));
    }
}
