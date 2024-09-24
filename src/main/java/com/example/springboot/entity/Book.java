package com.example.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;
    private int price;
    private String author;
    private int page;

    // fetch = FetchType.LAZY (default)
    // mappedBy : 연관관계의 주인이 내가 아니다.
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews; // 1:N으로 List로 받아옴
    // List나 Set으로 넣어주어야 함 - Set(중복불가), List(중복가능)
    // Object와 Object로 가리키고 있다는 뜻으로 DB상에선 보지 못함
}
