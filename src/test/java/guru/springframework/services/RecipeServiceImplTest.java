package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by jt on 6/17/17.
 */
public class RecipeServiceImplTest {
    
    RecipeServiceImpl recipeService;
    
    @Mock
<<<<<<< Updated upstream
    RecipeRepository recipeRepository;

=======
    RecipeReactiveRepository recipeReactiveRepository;
    
>>>>>>> Stashed changes
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
<<<<<<< Updated upstream

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
=======
        
        recipeService = new RecipeServiceImpl(recipeReactiveRepository, recipeCommandToRecipe, recipeToRecipeCommand);
>>>>>>> Stashed changes
    }
    
    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");
<<<<<<< Updated upstream
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById("1");

=======
        
        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        
        Recipe recipeReturned = recipeService.findById("1").block();
        
>>>>>>> Stashed changes
        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }
<<<<<<< Updated upstream

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById("1");

        //should go boom
    }

=======
    
    
>>>>>>> Stashed changes
    @Test
    public void getRecipeCommandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");
<<<<<<< Updated upstream
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

=======
        
        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        
>>>>>>> Stashed changes
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("1");
        
        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);
<<<<<<< Updated upstream

        RecipeCommand commandById = recipeService.findCommandById("1");

=======
        
        RecipeCommand commandById = recipeService.findCommandById("1").block();
        
>>>>>>> Stashed changes
        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }
    
    @Test
    public void getRecipesTest() throws Exception {
        
        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);
<<<<<<< Updated upstream

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

=======
        
        when(recipeService.getRecipes()).thenReturn(Flux.just(recipe));
        
        List<Recipe> recipes = recipeService.getRecipes().collectList().block();
        
>>>>>>> Stashed changes
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyString());
    }
    
    @Test
    public void testDeleteById() throws Exception {
        
        //given
        String idToDelete = "2";
<<<<<<< Updated upstream

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

=======
        
        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());
        
        //when
        recipeService.deleteById(idToDelete);
        
>>>>>>> Stashed changes
        //then
        verify(recipeRepository, times(1)).deleteById(anyString());
    }
}
