package com.gameart.backend.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Réponse standard de l'API")
public class ApiResponse<T> {
    
    @Schema(description = "Statut de la réponse", example = "success")
    private String status;

    @Schema(description = "Message de la réponse", example = "Opération réussie")
    private String message;

    @Schema(description = "Données de la réponse")
    private T data;

    @Schema(description = "Timestamp de la réponse")
    private LocalDateTime timestamp;

 
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(String status, String message, T data) {
        this();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", "Opération réussie", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("error", message, null);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>("error", message, data);
    }

 
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}