package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by jt on 6/28/17.
 */
public interface UnitOfMeasureService {
<<<<<<< Updated upstream

    Set<UnitOfMeasureCommand> listAllUoms();
=======
    Flux<UnitOfMeasureCommand> listAllUoms();
>>>>>>> Stashed changes
}
