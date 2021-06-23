package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by jt on 6/28/17.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
<<<<<<< Updated upstream
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

=======
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final UnitOfMeasureReactiveRepository unitOfMeasureRepository;
    
>>>>>>> Stashed changes
    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }
    
    @Override
<<<<<<< Updated upstream
    public IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        //enhance command object with recipe id
        IngredientCommand ingredientCommand = ingredientCommandOptional.get();
        ingredientCommand.setRecipeId(recipe.getId());

        return ingredientCommandOptional.get();
=======
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        
        return recipeReactiveRepository.findById(recipeId)
                .map(recipe -> recipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
                        .findFirst())
                .filter(Optional::isPresent)
                .map(ingredient -> {
                    IngredientCommand command = ingredientToIngredientCommand.convert(ingredient.get());
                    command.setRecipeId(recipeId);
                    return command;
                });
        
>>>>>>> Stashed changes
    }
    
    @Override
<<<<<<< Updated upstream
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

=======
    public Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command) {
        Recipe recipe = recipeReactiveRepository.findById(command.getRecipeId()).block();
        
        if (recipe == null) {
            
>>>>>>> Stashed changes
            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
<<<<<<< Updated upstream
            Recipe recipe = recipeOptional.get();

=======
>>>>>>> Stashed changes
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();
<<<<<<< Updated upstream

            if(ingredientOptional.isPresent()){
=======
            
            if (ingredientOptional.isPresent()) {
>>>>>>> Stashed changes
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
<<<<<<< Updated upstream
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
=======
                        .findById(command.getUom().getId()).block());
                
                if (ingredientFound.getUom() == null) {
                    new RuntimeException("UOM NOT FOUND");
                }
>>>>>>> Stashed changes
            } else {
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
              //  ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }
<<<<<<< Updated upstream

            Recipe savedRecipe = recipeRepository.save(recipe);

=======
            
            Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();
            
>>>>>>> Stashed changes
            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();
            
            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }
            
            //todo check for fail
            
            //enhance with id value
            IngredientCommand ingredientCommandSaved = ingredientToIngredientCommand.convert(savedIngredientOptional.get());
            ingredientCommandSaved.setRecipeId(recipe.getId());
<<<<<<< Updated upstream

            return ingredientCommandSaved;
=======
            
            return Mono.just(ingredientCommandSaved);
>>>>>>> Stashed changes
        }
        
    }
    
    @Override
<<<<<<< Updated upstream
    public void deleteById(String recipeId, String idToDelete) {

        log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
=======
    public Mono<Void> deleteById(String recipeId, String idToDelete) {
        
        log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);
        
        Recipe recipe = recipeReactiveRepository.findById(recipeId).block();
        
        if (recipe != null) {
            
>>>>>>> Stashed changes
            log.debug("found recipe");
            
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();
<<<<<<< Updated upstream

            if(ingredientOptional.isPresent()){
                log.debug("found Ingredient");
                Ingredient ingredientToDelete = ingredientOptional.get();
               // ingredientToDelete.setRecipe(null);
=======
            
            if (ingredientOptional.isPresent()) {
                log.debug("found Ingredient");
                
>>>>>>> Stashed changes
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }
<<<<<<< Updated upstream
=======
        
        return Mono.empty();
>>>>>>> Stashed changes
    }
}
