package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // order테이블에있는 member 필드에 의해 매핑된거야 order의 쫄따구, 거울일뿐이야, 읽기전용
    private List<Order> orders = new ArrayList<>();



}
