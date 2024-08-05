package com.libraryManagementSystem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository  extends JpaRepository<Patron , Integer> {
}
