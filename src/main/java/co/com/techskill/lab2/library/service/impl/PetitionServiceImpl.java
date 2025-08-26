package co.com.techskill.lab2.library.service.impl;

import co.com.techskill.lab2.library.domain.dto.PetitionDTO;
import co.com.techskill.lab2.library.service.IPetitionService;
import co.com.techskill.lab2.library.service.dummy.PetitionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class PetitionServiceImpl implements IPetitionService {

    private final PetitionService petitionService;

    public PetitionServiceImpl(PetitionService petitionService){
        this.petitionService = petitionService;
    }
    @Override
    public Flux<PetitionDTO> findALl() {
        return petitionService
                .dummyFindAll();
    }

    @Override
    public Mono<PetitionDTO> findById(String id) {
        return petitionService
                .dummyFindById(id);
    }

    @Override
    public Mono<PetitionDTO> save(PetitionDTO petitionDTO) {
        petitionDTO.setPetitionId(UUID.randomUUID().toString().substring(0,10));
        petitionDTO.setSentAt(LocalDate.now());
        return petitionService
                .dummySave(petitionDTO);
    }

    //TO-DO: Filter example findByPriority
    @Override
    public Flux<PetitionDTO> findByPriority(Integer p) {
        return petitionService.dummyFindAll()
                .filter(filterPriorityBy(p));
    }

    private Predicate<PetitionDTO> filterPriorityBy(Integer priority){
        return petitionDTO -> petitionDTO.getPriority().equals(priority);
    }

    //TO-DO: Check priorities with a delay of 1 second to show up the processing in console but requested in Swagger UI
    @Override
    public Flux<String> checkPrioritiesGreaterThanSeven() {
        return findALl()
                .limitRate(20)
                .filter(petitionDTO -> petitionDTO.getPriority()>=7)
                .map(petitionDTO -> LocalTime.now() + " - Check priority with level " + petitionDTO.getPriority()
                + ", Petition ID: " + petitionDTO.getPetitionId()
                +",  For Book ID:  " + petitionDTO.getBookId()+"\n")
                .delayElements(Duration.ofMillis(1000))
                .doOnNext(System.out::println);
    }






}
