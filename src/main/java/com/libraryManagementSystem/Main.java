package com.libraryManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class Main {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;

    public Main(BorrowingRecordRepository borrowingRecordRepository,
                PatronRepository patronRepository,
                BookRepository bookRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.patronRepository = patronRepository;
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    // APIs for Books

    //  Retrieve a list of all books
    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    //  Retrieve details of a specific book by ID
    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable int id) {
        return bookRepository.findById(id).orElse(null);
    }

    record NewBookRequest(
      String title ,
      String author,
      int publicationYear
    ){}

    // Add a new book to the library.

    @PostMapping("/api/books")
    public void addBook(@RequestBody NewBookRequest request) {
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setPublicationYear(request.publicationYear());
        bookRepository.save(book);
    }


    // Update an existing book's information.
    @PutMapping("/api/books/{id}")
    public void updateBook(@PathVariable int id, @RequestBody NewBookRequest request) {
        Book book = bookRepository.findById(id).orElse(null);
        assert book != null;
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setPublicationYear(request.publicationYear());
        bookRepository.save(book);
    }

    //  Remove a book from the library.
    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
    }


    // APIs for patrons

    //  Retrieve a list of all patrons
    @GetMapping("/api/patrons")
    public List<Patron> getPatrons() {
        return patronRepository.findAll();
    }

    //  Retrieve details of a specific patron by ID
    @GetMapping("/api/patrons/{id}")
    public Patron getPatron(@PathVariable int id) {
        return patronRepository.findById(id).orElse(null);
    }


    record NewPatronRequest(
            String name,
            String contactInformation
    ){}

    // Add  patron to the system.

    @PostMapping("/api/patrons")
    public void addPatron(@RequestBody NewPatronRequest request) {
        Patron patron = new Patron();
        patron.setName(request.name());
        patron.setContactInformation(request.contactInformation());
        patronRepository.save(patron);
    }


    // Update an existing patron's information.
    @PutMapping("/api/patrons/{id}")
    public void updatePatron(@PathVariable int id, @RequestBody NewPatronRequest request) {
        Patron patron = patronRepository.findById(id).orElse(null);
        assert patron != null;
        patron.setName(request.name());
        patron.setContactInformation(request.contactInformation());
        patronRepository.save(patron);
    }

    // Remove a patron from the system
    @DeleteMapping("/api/patrons/{id}")
    public void deletePatron(@PathVariable int id) {
        patronRepository.deleteById(id);
    }


    // APIs for Borrowing Records

    record NewBorrowingRequest(
             Integer bookID,
             int patronID,
             String borrowingDate,
             String returnDate

    ){}

    //  Allow a patron to borrow a book.
    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> patronBorrowingABook(@PathVariable int bookId, @PathVariable int patronId, @RequestBody NewBorrowingRequest request) {

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {

            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBookID(request.bookID());
            borrowingRecord.setPatronID(request.patronID());
            borrowingRecord.setBorrowingDate(request.borrowingDate());
            borrowingRecord.setReturnDate(request.returnDate());

            borrowingRecordRepository.save(borrowingRecord);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book or Patron not found");
        }

    }

    // Record the return of a borrowed book by a patron.
    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable int bookId, @PathVariable int patronId) {
        Optional<BorrowingRecord> optionalBorrowingRecord = borrowingRecordRepository.findByBookIDAndPatronID(bookId, patronId);

        if (optionalBorrowingRecord.isPresent()) {
            BorrowingRecord borrowingRecord = optionalBorrowingRecord.get();
            borrowingRecord.setReturnDate(java.time.LocalDate.now().toString());
            borrowingRecordRepository.save(borrowingRecord);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrowing record not found");
        }
    }

}

