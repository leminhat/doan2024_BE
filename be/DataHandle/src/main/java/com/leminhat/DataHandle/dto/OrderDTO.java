package com.leminhat.DataHandle.dto;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
        {
                "id",
//                "create_at",
                "discounte",
                "order_status",
                "toltal_discounted_price",
                "total_item",
                "total_price",
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDTO {

        @JsonProperty("id")
        private long id;


//        @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "epoch_millis")
//        @JsonProperty("create_at")
//        private LocalDateTime createAt;

        @JsonProperty("discounte")
        private Long discounte;

        @JsonProperty("order_status")
        private String status;

        @JsonProperty("toltal_discounted_price")
        private Long toltalDiscountedPrice;

        @JsonProperty("total_item")
        private int totalItem;

        @JsonProperty("total_price")
        private Long totalPrice;




}
