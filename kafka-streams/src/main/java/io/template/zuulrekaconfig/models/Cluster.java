package io.template.zuulrekaconfig.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cluster")
@Table(name = "cluster")
public class Cluster implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "longitude")
    Double longitude;

    @Column(name = "latitude")
    Double latitude;

    @Column(name = "clustername")
    String clusterName;

    @Column(name = "username")
    String username;

    @Column(name = "happy")
    Double happy;

    @Column(name = "sad")
    Double sad;

    @Column(name = "neutral")
    Double neutral;
    
    @Column(name = "createdat")
    Instant createdAt = Instant.now();

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getHappy() {
        return happy;
    }

    public void setHappy(Double happy) {
        this.happy = happy;
    }

    public Double getSad() {
        return sad;
    }

    public void setSad(Double sad) {
        this.sad = sad;
    }

    public Double getNeutral() {
        return neutral;
    }

    public void setNeutral(Double neutral) {
        this.neutral = neutral;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
