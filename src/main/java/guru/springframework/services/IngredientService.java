package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 * Created by jt on 6/27/17.
 */
public interface IngredientService {
<<<<<<< Updated upstream

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(String recipeId, String idToDelete);
=======
    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    
    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);
    
    Mono<Void> deleteById(String recipeId, String idToDelete);
>>>>>>> Stashed changes
}
