package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {
<<<<<<< Updated upstream

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeCommand findCommandById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(String idToDelete);
=======
    Flux<Recipe> getRecipes();
    
    Mono<Recipe> findById(String id);
    
    Mono<RecipeCommand> findCommandById(String id);
    
    Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command);
    
    Mono<Void> deleteById(String idToDelete);
>>>>>>> Stashed changes
}
