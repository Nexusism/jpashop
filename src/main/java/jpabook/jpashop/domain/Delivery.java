package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //기본값이 ORDINAL인데 숫자로들어가서 중간에 상태가 껴들면 망함, 꼭 STRING으로 써야함.
    private DeliveryStatus status; //READY, COMP
}
