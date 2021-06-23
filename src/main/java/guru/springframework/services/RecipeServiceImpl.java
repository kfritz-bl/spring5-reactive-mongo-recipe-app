package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
<<<<<<< Updated upstream

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
=======
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    
    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeReactiveRepository = recipeReactiveRepository;
>>>>>>> Stashed changes
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }
    
    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
    
    @Override
    public Recipe findById(String id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found. For ID value: " + id );
        }

        return recipeOptional.get();
    }
    
    @Override
<<<<<<< Updated upstream
    @Transactional
    public RecipeCommand findCommandById(String id) {

        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(findById(id));

        //enhance command object with id value
        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients().forEach(rc -> {
                rc.setRecipeId(recipeCommand.getId());
            });
        }

        return recipeCommand;
=======
    public Mono<RecipeCommand> findCommandById(String id) {
        
        return recipeReactiveRepository.findById(id)
                .map(recipe -> {
                    RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
                    
                    recipeCommand.getIngredients().forEach(rc -> {
                        rc.setRecipeId(recipeCommand.getId());
                    });
                    
                    return recipeCommand;
                });
>>>>>>> Stashed changes
    }
    
    @Override
<<<<<<< Updated upstream
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
=======
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        
        return recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
                .map(recipeToRecipeCommand::convert);
>>>>>>> Stashed changes
    }
    
    @Override
<<<<<<< Updated upstream
    public void deleteById(String idToDelete) {
        recipeRepository.deleteById(idToDelete);
=======
    public Mono<Void> deleteById(String idToDelete) {
        recipeReactiveRepository.deleteById(idToDelete).block();
        
        return Mono.empty();
>>>>>>> Stashed changes
    }
}
