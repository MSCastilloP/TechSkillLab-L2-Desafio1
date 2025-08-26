package co.com.techskill.lab2.library.repository;

import co.com.techskill.lab2.library.domain.entity.Petition;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IPetitionRepository  {
    Mono<Petition> findByPetitionId(String id);
}
