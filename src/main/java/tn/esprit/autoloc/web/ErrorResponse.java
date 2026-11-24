package tn.esprit.autoloc.web;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ErrorResponse {
    private final int statut;
    private final String message;
    private final LocalDateTime horodatage;

    public ErrorResponse(int statut, String message) {
        this.statut = statut;
        this.message = message;
        this.horodatage = LocalDateTime.now(ZoneId.of("Africa/Tunis"));
    }

    // Getters (nécessaires pour que Jackson génère le JSON)
    public int getStatut() { return statut; }
    public String getMessage() { return message; }
    public LocalDateTime getHorodatage() { return horodatage; }
}
