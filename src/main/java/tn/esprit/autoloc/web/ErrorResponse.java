package tn.esprit.autoloc.web;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int statut;
    private String message;
    private LocalDateTime horodatage;

    public ErrorResponse(int statut, String message) {
        this.statut = statut;
        this.message = message;
        this.horodatage = LocalDateTime.now();
    }

    // Getters (nécessaires pour que Jackson génère le JSON)
    public int getStatut() { return statut; }
    public String getMessage() { return message; }
    public LocalDateTime getHorodatage() { return horodatage; }
}