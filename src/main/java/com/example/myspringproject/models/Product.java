package com.example.myspringproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, columnDefinition = "text", unique = true)
    @NotEmpty(message = "Наименование не должно быть пустым")
    private String title;

    @Column(name = "descripyion", nullable = false, columnDefinition = "text")
    @NotEmpty(message = "Описание не должно быть пустым")
    private String descripyion;

    @Column(name = "price", nullable = false)
    @Min(value = 1, message = "Цена товара не может быть отрицательной или равняться нулю")
    private float price;

    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> imageList = new ArrayList<>();

    //метод добавления фотографий в лист текуего продукта
    public void addImageToProduct(Image image){
        image.setProduct(this);
        imageList.add(image);
    }
    private LocalDateTime dateTime;

    //Метод заполнения даты и времени при создании обектов класса
    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

    public Product(String title, String descripyion, float price, Category category, List<Image> imageList, LocalDateTime dateTime) {
        this.title = title;
        this.descripyion = descripyion;
        this.price = price;
        this.category = category;
        this.imageList = imageList;
        this.dateTime = dateTime;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripyion() {
        return descripyion;
    }

    public void setDescripyion(String descripyion) {
        this.descripyion = descripyion;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
