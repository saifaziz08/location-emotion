package io.template.zuulrekaconfig.repository;

import io.template.zuulrekaconfig.models.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClusterRepository extends JpaRepository<Cluster, Integer> {
    @Query("SELECT c FROM Cluster c WHERE c.username = :username AND c.happy > 0.5 ORDER BY c.createdAt")
    List<Cluster> getHappyClusters(String username);

    @Query("SELECT c FROM  Cluster c WHERE c.username = :username AND c.sad > 0.5 ORDER BY c.createdAt")
    List<Cluster> getSadClusters(String username);

    @Query("SELECT c FROM Cluster c WHERE c.username = :username AND c.neutral > 0.5 ORDER BY c.createdAt")
    List<Cluster> getNeutralClusters(String username);
}