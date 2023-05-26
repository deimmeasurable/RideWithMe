package com.example.ridewithme.repository;

import com.example.ridewithme.models.CommissionAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionAgentRepository extends JpaRepository<CommissionAgent,Long> {
}
