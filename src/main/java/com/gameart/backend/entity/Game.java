package com.gameart.backend.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    @NotBlank
    private String platform;

    @NotBlank
    private String genre;

    @DecimalMin("0.0")
    private Double price;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double rating;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Min(0)
    private Integer stock;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "cover_image")
    private String coverImage;

    @ElementCollection
    @CollectionTable(name = "game_images", joinColumns = @JoinColumn(name = "game_id"))
    private List<String> images;

    @Column(name = "url_trailer")
    private String urlTrailer;

    private Boolean promo;
    private Boolean popular;

    @ElementCollection
    @CollectionTable(name = "game_tags", joinColumns = @JoinColumn(name = "game_id"))
    private List<String> tags;


    public Game() {}

    public Game(String id, String title, String platform, String genre, Double price, 
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