package io.template.zuulrekaconfig.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Recalculation")
@Table(name = "recalculation")
public class RecalculationQueue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "requestid")
    String requestId;
    public RecalculationQueue(RecalculationQueueBuilder builder) {
        this.requestId = builder.requestId;
    }

    public static class RecalculationQueueBuilder{
        String requestId;
        public RecalculationQueueBuilder(){}
        public RecalculationQueueBuilder request(String requestId){
            this.requestId = requestId;
            return this;
        }
        public RecalculationQueue build(){
            return new RecalculationQueue(this);
        }
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
