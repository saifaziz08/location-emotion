package io.template.zuulrekaconfig.repository;

import io.template.zuulrekaconfig.models.RecalculationQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecalculationRepository extends JpaRepository<RecalculationQueue, Integer> {
    List<RecalculationQueue> findByRequestId(String requestId);
}
