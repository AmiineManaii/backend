package com.gameart.backend.dto;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO pour les jeux vidéo")
public class GameDTO {
    
    @Schema(description = "ID du jeu", example = "1")
    private String id;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 2, max = 255, message = "Le titre doit contenir entre 2 et 255 caractères")
    @Schema(description = "Titre du jeu", example = "Cyberpunk 2077")
    private String title;

    @NotBlank(message = "La plateforme est obligatoire")
    @Schema(description = "Plateforme du jeu", example = "PC")
    private String platform;

    @NotBlank(message = "Le genre est obligatoire")
    @Schema(description = "Genre du jeu", example = "RPG")
    private String genre;

    @DecimalMin(value = "0.0", message = "Le prix ne peut pas être négatif")
    @Schema(description = "Prix du jeu", example = "59.99")
    private Double price;

    @DecimalMin(value = "0.0", message = "La note ne peut pas être inférieure à 0")
    @DecimalMax(value = "5.0", message = "La note ne peut pas être supérieure à 5")
    @Schema(description = "Note du jeu", example = "4.5")
    private Double rating;

    @Schema(description = "Date de sortie", example = "2020-12-10")
    private LocalDate releaseDate;

    @Min(value = 0, message = "Le stock ne peut pas être négatif")
    @Schema(description = "Stock disponible", example = "12")
    private Integer stock;

    @Size(max = 1000, message = "La description ne peut pas dépasser 1000 caractères")
    @Schema(description = "Description du jeu", example = "Incarnez V, un mercenaire dans la mégalopole futuriste...")
    private String description;

    @Schema(description = "URL de l'image de couverture", example = "/assets/games/cover/cyberpunk.jpg")
    private String coverImage;

    @Schema(description = "Liste des images du jeu")
    private List<String> images;

    @Schema(description = "URL de la bande-annonce", example = "https://www.youtube.com/watch?v=8X2kIfS6fb8")
    private String urlTrailer;

    @Schema(description = "Indique si le jeu est en promotion", example = "true")
    private Boolean promo;

    @Schema(description = "Indique si le jeu est populaire", example = "false")
    private Boolean popular;

    @Schema(description = "Liste des tags du jeu")
    private List<String> tags;



    public GameDTO() {}

    public GameDTO(String id, String title, String platform, String genre, Double price, 
                   Double rating, LocalDate releaseDate, Integer stock, String description, 
                   String coverImage, List<String> images, String urlTrailer, 
                   Boolean promo, Boolean popular, List<String> tags) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.genre = genre;
        this.price = price;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.stock = stock;
        this.description = description;
        this.coverImage = coverImage;
        this.images = images;
        this.urlTrailer = urlTrailer;
        this.promo = promo;
        this.popular = popular;
        this.tags = tags;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public String getUrlTrailer() { return urlTrailer; }
    public void setUrlTrailer(String urlTrailer) { this.urlTrailer = urlTrailer; }

    public Boolean getPromo() { return promo; }
    public void setPromo(Boolean promo) { this.promo = promo; }

    public Boolean getPopular() { return popular; }
    public void setPopular(Boolean popular) { this.popular = popular; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}