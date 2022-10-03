package org.example.repository;
import org.example.models.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Integer> {
    List<Metrics> findByRequestId(String requestId);
}
