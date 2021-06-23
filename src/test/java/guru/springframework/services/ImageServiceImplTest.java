package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ImageServiceImplTest {
    
    @Mock
<<<<<<< Updated upstream
    RecipeRepository recipeRepository;

=======
    RecipeReactiveRepository recipeReactiveRepository;
    
>>>>>>> Stashed changes
    ImageService imageService;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
<<<<<<< Updated upstream

        imageService = new ImageServiceImpl(recipeRepository);
=======
        
        imageService = new ImageServiceImpl(recipeReactiveRepository);
>>>>>>> Stashed changes
    }
    
    @Test
    public void saveImageFile() throws Exception {
        //given
        String id = "1";
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());
        
        Recipe recipe = new Recipe();
        recipe.setId(id);
<<<<<<< Updated upstream
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

=======
        
        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));
        
>>>>>>> Stashed changes
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        
        //when
        imageService.saveImageFile(id, multipartFile);
        
        //then
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
    
}
