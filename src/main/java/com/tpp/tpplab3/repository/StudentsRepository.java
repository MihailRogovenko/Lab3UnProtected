package com.tpp.tpplab3.repository;

import com.tpp.tpplab3.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {
}
