package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClientDto {
    private Long idClient;

    @NotBlank(message = "Le nom du client est obligatoire.")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères.")
    private String nom;

    @NotBlank(message = "Le prénom du client est obligatoire.")
    @Size(max = 100, message = "Le prénom ne peut pas dépasser 100 caractères.")
    private String prenom;

    @NotBlank(message = "L'adresse e-mail est obligatoire.")
    @Email(message = "L'adresse e-mail n'est pas valide.")
    @Size(max = 255, message = "L'adresse e-mail ne peut pas dépasser 255 caractères.")
    private String email;

    @Size(max = 20, message = "Le téléphone ne peut pas dépasser 20 caractères.")
    private String telephone;

    @NotBlank(message = "Le numéro de permis est obligatoire.")
    @Size(max = 50, message = "Le numéro de permis ne peut pas dépasser 50 caractères.")
    private String numPermis;

    @NotNull(message = "La date d'inscription est obligatoire.")
    private LocalDate dateInscription;
}
