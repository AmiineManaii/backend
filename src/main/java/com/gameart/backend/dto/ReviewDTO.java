package com.gameart.backend.dto;

import java.time.LocalDateTime;

import com.gameart.backend.entity.Game;
import com.gameart.backend.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO pour les avis")
public class ReviewDTO {
    
    @Schema(description = "ID de l'avis", example = "rev1")
    private String id;

    @NotBlank(message = "L'ID du jeu est obligatoire")
    @Schema(description = "ID du jeu", example = "1")
    private Game game;

    @NotBlank(message = "L'ID utilisateur est obligatoire")
    @Schema(description = "ID de l'utilisateur", example = "1")
    private User user;

    @NotBlank(message = "Le message est obligatoire")
    @Size(max = 500, message = "Le message ne peut pas dépasser 500 caractères")
    @Schema(description = "Message de l'avis", example = "Incroyable ! L'atmosphère de Night City est unique.")
    private String msg;

    @NotNull(message = "La note est obligatoire")
    @Min(value = 1, message = "La note doit être au moins 1")
    @Max(value = 5, message = "La note ne peut pas dépasser 5")
    @Schema(description = "Note de l'avis", example = "5")
    private Integer note;

    @Schema(description = "Date de l'avis")
    private LocalDateTime date;

    @Schema(description = "Indique si l'avis est vérifié", example = "true")
    private Boolean verified;

   
    public ReviewDTO() {}

    public ReviewDTO(String id, Game game, User user, String msg, 
                     Integer note, LocalDateTime date, Boolean verified) {
        this.id = id;
        this.game = game;
        this.user = user;
        this.msg = msg;
        this.note = note;
        this.date = date;
        this.verified = verified;
    }

  
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public Integer getNote() { return note; }
    public void setNote(Integer note) { this.note = note; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }
}