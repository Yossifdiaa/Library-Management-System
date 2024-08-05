package com.libraryManagementSystem;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class BorrowingRecord {

    @Id
    @SequenceGenerator(
            name = "borrowingRecord_id_sequence",
            sequenceName = "borrowingRecord_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "borrowingRecord_id_sequence"
    )
    private Integer bookID;
    private int patronID;
    private String borrowingDate;
    private String returnDate;

    public BorrowingRecord(int bookID, int patronID, String borrowingDate, String returnDate) {
        this.bookID = bookID;
        this.patronID = patronID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }


    public BorrowingRecord() {
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public int getPatronID() {
        return patronID;
    }

    public void setPatronID(int patronID) {
        this.patronID = patronID;
    }

    public String getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(String borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowingRecord that = (BorrowingRecord) o;
        return bookID == that.bookID && patronID == that.patronID && Objects.equals(borrowingDate, that.borrowingDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, patronID, borrowingDate, returnDate);
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "bookID=" + bookID +
                ", patronID=" + patronID +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                '}';
    }
}


