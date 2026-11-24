package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgenceDto {
    private Long idAgence;

    @NotBlank(message = "Le nom de l'agence est obligatoire.")
    @Size(max = 100, message = "Le nom de l'agence ne peut pas dépasser 100 caractères.")
    private String nom;

    @NotBlank(message = "La ville est obligatoire.")
    @Size(max = 100, message = "La ville ne peut pas dépasser 100 caractères.")
    private String ville;

    @NotBlank(message = "L'adresse est obligatoire.")
    @Size(max = 255, message = "L'adresse ne peut pas dépasser 255 caractères.")
    private String adresse;

    @NotBlank(message = "Le téléphone de l'agence est obligatoire.")
    @Size(max = 20, message = "Le téléphone ne peut pas dépasser 20 caractères.")
    private String telephone;
}
