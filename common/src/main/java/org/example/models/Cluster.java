package org.example.models;

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

    public Cluster(ClusterBuilder builder){
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.clusterName = builder.clusterName;
        this.username = builder.username;
        this.happy = builder.happy;
        this.sad = builder.sad;
        this.neutral = builder.neutral;
        this.createdAt = Instant.now();
    }

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

    public static class ClusterBuilder{
        Double longitude;
        Double latitude;
        String clusterName;
        String username;
        Double happy;
        Double sad;
        Double neutral;

        public ClusterBuilder(){ }

        public ClusterBuilder longitude(Double longitude){
            this.longitude = longitude;
            return this;
        }

        public ClusterBuilder latitude(Double latitude){
            this.latitude = latitude;
            return this;
        }

        public ClusterBuilder happy(Double happy){
            this.happy = happy;
            return this;
        }

        public ClusterBuilder sad(Double sad){
            this.sad = sad;
            return this;
        }

        public ClusterBuilder neutral(Double neutral){
            this.neutral = neutral;
            return this;
        }

        public ClusterBuilder username(String username){
            this.username = username;
            return this;
        }
        public ClusterBuilder clusterName(String clusterName) {
            this.clusterName = clusterName;
            return  this;
        }

        public Cluster build(){
            return new Cluster(this);
        }




    }
}
