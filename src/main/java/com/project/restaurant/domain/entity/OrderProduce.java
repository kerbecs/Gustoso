package com.project.restaurant.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "order_produce")
public class OrderProduce {
    @EmbeddedId
    private OrderProduceId orderProduceId;

    @MapsId("order")
    @ManyToOne
    @JsonIgnoreProperties
    private Order order;

    @MapsId("produce")
    @ManyToOne
    @JsonIgnoreProperties
    private Produce produce;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class OrderProduceId implements Serializable {
        @ManyToOne
        @JoinColumn(name = "order_id")
        private Order order;

        @ManyToOne
        @JoinColumn(name = "produce_id")
        private Produce produce;
    }
    @Column(nullable = false)
    private Double cost;

    @Builder
    public OrderProduce(Order order, Produce produce, Double cost) {
        this.order = order;
        this.produce = produce;
        this.cost = cost;

        orderProduceId = new OrderProduceId(order,produce);
    }

}
