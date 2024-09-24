package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cost;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt=new Date();

    @ManyToOne // 관계설정 : BOOK-REVIEW가 N:1 관계를 가지고 있다
    // fetch = FetchType.EAGER (default)
    // HEY JPA!!! Book book은 테이블의 컬럼으로 만들지 말고 FK로 만들어다오
    @JoinColumn(name="book_id", referencedColumnName = "id", nullable = false) // FK
    // referencedColumnName = "id" 생략 가능
    // @JsonIgnore // ▣1. JsonIgnore - 순환참조 문제 해결
    private Book book; // book_PK(id)
}
