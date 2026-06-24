package com.example.app.repository;

import com.example.app.entity.SuccessorCause;
import com.example.app.entity.SuccessorCauseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessorCauseRepository extends JpaRepository<SuccessorCause, SuccessorCauseId> {
}
