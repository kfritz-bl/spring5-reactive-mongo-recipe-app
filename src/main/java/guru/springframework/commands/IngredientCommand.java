package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< Updated upstream
=======
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
>>>>>>> Stashed changes
import java.math.BigDecimal;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private String id;
    private String recipeId;
<<<<<<< Updated upstream
    private String description;
    private BigDecimal amount;
=======
    
    @NotBlank
    private String description;
    
    @NotNull
    @Min(1)
    private BigDecimal amount;
    
    @NotNull
>>>>>>> Stashed changes
    private UnitOfMeasureCommand uom;
}
