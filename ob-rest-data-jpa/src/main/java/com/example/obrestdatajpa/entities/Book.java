package com.example.obrestdatajpa.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@ApiModel("Entidad libro para representar una entidad en JPA")
public class Book {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave primaria ficticia autoincremental tipo Long")
    private Long id;
    private String title;
    private String autor;
    private Integer nPages;
    @ApiModelProperty("Precio en la moneda EUR")
    private Double price;
    private LocalDate releaseDate;
    private Boolean eBook;

    //Constructores


    public Book() {
    }

    public Book(Long id, String title, String autor, Integer nPages, Double price, LocalDate releaseDate, Boolean eBook) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.nPages = nPages;
        this.price = price;
        this.releaseDate = releaseDate;
        this.eBook = eBook;
    }


    // Getter y Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getnPages() {
        return nPages;
    }

    public void setnPages(Integer nPages) {
        this.nPages = nPages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean geteBook() {
        return eBook;
    }

    public void seteBook(Boolean eBook) {
        this.eBook = eBook;
    }


    // toString()


    @Override
    public String toString() {
        return "\nBook{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", autor='" + autor + '\'' +
                ", nPages=" + nPages +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", eBook=" + eBook +
                '}';
    }
}
