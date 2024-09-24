package com.example.springboot.controller;

import com.example.springboot.entity.Book;
import com.example.springboot.entity.BookDTO;
import com.example.springboot.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Book REST API", description = "책 API 서비스입니다.") // API 이름, 설명
public class BookRestController {

    public final BookService bookService;
    // GET : http://localhost:8081/api/book
    @GetMapping("/book")
    @Tag(name = "Book REST API")
    @Operation(summary = "Book 조회", description = "책 조회 서비스입니다.")
    public List<BookDTO> books(){
        List<BookDTO> books = bookService.getAllBookDTO();
        return books; // JSON(MessageConverter: 순환참조문제)
    }

    @PostMapping("/book")
    public BookDTO register(@RequestBody BookDTO dto){
        return null;
    }
}
