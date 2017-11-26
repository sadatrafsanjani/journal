package com.rafsan.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id")
    private User author;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    private String picture;

    @NotEmpty
    private String title;

    @NotEmpty
    private String body;

    @Column(insertable = false)
    private String published;

    @Column(insertable = false)
    private boolean visibility;

    public Post(){}

    public Post(Long id, User author, Category category,
                String picture, String title, String body, String published, boolean visibility) {
        this.id = id;
        this.author = author;
        this.category = category;
        this.picture = picture;
        this.title = title;
        this.body = body;
        this.published = published;
        this.visibility = visibility;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author +
                ", category=" + category +
                ", picture='" + picture + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", published='" + published + '\'' +
                ", visibility=" + visibility +
                '}';
    }
}
