package com.example.springboot.service;

import com.example.springboot.entity.Book;
import com.example.springboot.entity.BookDTO;
import com.example.springboot.entity.Review;
import com.example.springboot.entity.ReviewDTO;
import com.example.springboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        return bookRepository.findAllWithReviews();
    }
    @Transactional(readOnly = true) // 생략가능
    public List<BookDTO> getAllBookDTO(){
        // Book 정보와 Review 정보 함께 가져오기
        // ★1. 서비스 계층에서 reviews를 미리 로드하여, 뷰 계층에서 LazyInitializationException 방지
//        List<Book> books = bookRepository.findAll();
//        books.forEach(book -> Hibernate.initialize(book.getReviews()));
        // ▣2. JSON 변환시 Entity를 DTO로 변환 - 순환참조 문제 해결
        List<Book> books = bookRepository.findAllWithReviews();
        // Book <---순환참조---> Review
        List<BookDTO> bookDTOS = books.stream().map(this::convertToDTO).collect(Collectors.toList());
        return bookDTOS;
    }
    // Book -> BookDTO로 옮기기
    private BookDTO convertToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPage(book.getPage());
        // Review -> ReviewDTO
        List<ReviewDTO> reviews=book.getReviews().stream().map(this::convertToDTO).collect(Collectors.toList());
        bookDTO.setReviews(reviews);
        return bookDTO;
    }

    // Review -> ReviewDTO로 옮기기
    private ReviewDTO convertToDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setCost(review.getCost());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setCreatedAt(review.getCreatedAt());
        return reviewDTO;
    }

    // save
    public Book save(Book book){
        return bookRepository.save(book);
    }

}