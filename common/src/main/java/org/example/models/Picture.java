package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Picture")
@Table(name = "picture")
public class Picture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "requestid")
    String requestId;

    @Column(name = "picturepath")
    String picturePath;

    @Column(name = "longitude")
    Double longitude;

    @Column(name = "latitude")
    Double latitude;

    @Column(name = "username")
    String username;

    public Picture(PictureCaptureBuilder builder) {
        this.requestId = builder.requestId;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.picturePath = builder.pathToPicture;
        this.username = builder.username;
    }

    public static class PictureCaptureBuilder {
        String requestId;
        String username;
        String pathToPicture;
        Double longitude;
        Double latitude;

        public PictureCaptureBuilder() {
        }

        public PictureCaptureBuilder requestId(String id) {
            this.requestId = id;
            return this;
        }

        public PictureCaptureBuilder username(String username) {
            this.username = username;
            return this;
        }

        public PictureCaptureBuilder pathToPicture(String pathToPicture) {
            this.pathToPicture = pathToPicture;
            return this;
        }

        public PictureCaptureBuilder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public PictureCaptureBuilder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Picture build() {
            return new Picture(this);
        }

    }


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}