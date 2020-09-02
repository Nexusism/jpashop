package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class Movie extends Item{ //Item 상속클래스

    private  String director;
    private  String actor;
}
