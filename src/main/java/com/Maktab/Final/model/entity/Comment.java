package com.Maktab.Final.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String comment;
    private Boolean point;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;

    @Builder
    public Comment(Integer id, String comment, Boolean point, Customer customer, Expert expert) {
        this.id = id;
        this.comment = comment;
        this.point = point;
        this.customer = customer;
        this.expert = expert;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", point=" + point +
                ", customer=" + customer +
                ", expert=" + expert +
                '}';
    }
}
