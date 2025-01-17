package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
<<<<<<< Updated upstream


    private final RecipeRepository recipeRepository;

    public ImageServiceImpl( RecipeRepository recipeService) {

        this.recipeRepository = recipeService;
    }

    @Override
    @Transactional
    public void saveImageFile(String recipeId, MultipartFile file) {

        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);

            e.printStackTrace();
        }
=======
    private final RecipeReactiveRepository recipeReactiveRepository;
    
    public ImageServiceImpl(RecipeReactiveRepository recipeService) {
        this.recipeReactiveRepository = recipeService;
    }
    
    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {
        
        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    Byte[] byteObjects = new Byte[0];
                    try {
                        byteObjects = new Byte[file.getBytes().length];
                        
                        int i = 0;
                        
                        for (byte b : file.getBytes()) {
                            byteObjects[i++] = b;
                        }
                        
                        recipe.setImage(byteObjects);
                        
                        return recipe;
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
        
        recipeReactiveRepository.save(recipeMono.block()).block();
        
        return Mono.empty();
>>>>>>> Stashed changes
    }
}
