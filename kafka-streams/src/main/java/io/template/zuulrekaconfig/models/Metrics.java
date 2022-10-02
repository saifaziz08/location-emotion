package io.template.zuulrekaconfig.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Metrics")
@Table(name = "metrics")
public class Metrics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "requestid")
    String requestId;

    @Column(name = "longitude")
    Double longitude;
    @Column(name = "latitude")
    Double latitude;

    @Column(name = "clusterid")
    Integer clusterId;

    @Column(name = "algorithm")
    String algorithm;

    @Column(name = "happy")
    Double happy;

    @Column(name = "sad")
    Double sad;

    @Column(name = "neutral")
    Double neutral;

    @Column(name = "metrictype")
    String metricType;
    @Column(name = "metricvalue")
    Double metricValue;

    Metrics(MetricsBuilder builder) {
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.clusterId = builder.clusterId;
        this.algorithm = builder.algorithm;
        this.metricType = builder.metricType;
        this.metricValue = builder.metricValue;
        this.requestId = builder.requestId;
    }

    public static class MetricsBuilder {
        public MetricsBuilder() {

        }
        String requestId;
        Double longitude;
        Double latitude;
        Integer clusterId;
        String algorithm;
        String metricType;
        Double metricValue;

        public Metrics build() {
            return new Metrics(this);
        }

        public MetricsBuilder request(String requestId){
            this.requestId = requestId;
            return this;
        }

        public MetricsBuilder metricType(String metricType){
            this.metricType = metricType;
            return this;
        }

        public MetricsBuilder metricValue(Double metricValue){
            this.metricValue = metricValue;
            return this;
        }


        public MetricsBuilder longitude(Double longitude){
            this.longitude = longitude;
            return this;
        }

        public MetricsBuilder latitude(Double latitude){
            this.latitude = latitude;
            return this;
        }

        public MetricsBuilder cluster(Integer clusterId){
            this.clusterId = clusterId;
            return this;
        }

        public MetricsBuilder algorithm(String algorithm){
            this.algorithm = algorithm;
            return this;
        }
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

    public Integer getClusterId() {
        return clusterId;
    }

    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
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
}
