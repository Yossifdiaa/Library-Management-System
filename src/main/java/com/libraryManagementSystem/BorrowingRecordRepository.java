package com.libraryManagementSystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord , Integer> {
    Optional<BorrowingRecord> findByBookIDAndPatronID(int bookID, int patronID);
}
