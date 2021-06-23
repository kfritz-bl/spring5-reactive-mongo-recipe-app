package guru.springframework.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by jt on 7/3/17.
 */
public interface ImageService {
<<<<<<< Updated upstream

    void saveImageFile(String recipeId, MultipartFile file);
=======
    Mono<Void> saveImageFile(String recipeId, MultipartFile file);
>>>>>>> Stashed changes
}
