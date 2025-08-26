package co.com.techskill.lab2.library.service;

import co.com.techskill.lab2.library.domain.dto.PetitionDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPetitionService {
    Flux<PetitionDTO> findALl();
    Mono<PetitionDTO> findById(String id);
    Mono<PetitionDTO> save (PetitionDTO petitionDTO);

    Flux<PetitionDTO> findByPriority(Integer p);
    Flux<String> checkPrioritiesGreaterThanSeven();


}
