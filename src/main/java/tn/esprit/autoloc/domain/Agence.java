package tn.esprit.autoloc.domain;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;
    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    //relation OneToMany vers Vehicule
    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Vehicule> vehicules = new HashSet<>();

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private Set<Employe> employes = new HashSet<>();
}