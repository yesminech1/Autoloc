package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeDto {
    private Long idEmploye;

    @NotBlank(message = "Le nom de l'employé est obligatoire.")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères.")
    private String nom;

    @NotBlank(message = "Le prénom de l'employé est obligatoire.")
    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères.")
    private String prenom;

    @NotBlank(message = "Le rôle de l'employé est obligatoire.")
    @Size(max = 20, message = "Le rôle ne peut pas dépasser 20 caractères.")
    private String role;

    @Positive(message = "L'identifiant de l'agence doit être positif.")
    private Long agenceId;
}
