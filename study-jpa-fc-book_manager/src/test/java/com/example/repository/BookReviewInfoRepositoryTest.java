package com.example.repository;

import com.example.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        System.out.println(bookReviewInfoRepository.save(bookReviewInfo));

    }

    @Test
    void crudTest2() {
        givenBookReviewInfo();

        BookReviewInfo result = bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new);

        BookReviewInfo result2 = bookRepository
                .findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();

        System.out.println(result);
        System.out.println(">>>>>>>>>2" + result2);

    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("JPA 책인듯");
        book.setAuthorId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        Member user = memberRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println(user.getReviews().get(0).getBook());
        System.out.println(user.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenUsers(), givenBook(givenPublisher()));

    }

    private Member givenUsers() {
        return memberRepository.findById(1L).orElseThrow(RuntimeException::new);
    }

    private Book givenBook(Publisher publisher) {
        return bookRepository.save(Book
                .builder()
                .name("JPA 책책책")
                .publisher(publisher)
                .build());
    }

    private Publisher givenPublisher() {
        return publisherRepository.save(
                Publisher.builder()
                        .name("킹영환")
                        .build());
    }

    private void givenReview(Member member, Book book) {

        Review review = new Review();

        review.setTitle("책이 으디보자..");
        review.setContent("괜찮은듯?");
        review.setScore(3.0f);
        review.setMember(member);
        review.setBook(book);

        reviewRepository.save(review);
    }

}