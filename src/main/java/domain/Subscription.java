package domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    int id;

    String name;

    @Column(name = "price_from")
    BigDecimal priceFrom;

    @Column(name = "price_to")
    BigDecimal priceTo;

    @ManyToOne
    @JoinColumn(name = "FK_Sub_Rubric")
    Rubric rubric;

    @ManyToOne
    @JoinColumn(name = "FK_Sub_Author")
    Author author;
}

/*
* Rubric <- Subscription -> Author -> Email
*
* Subscription("bmw", 150, 300) - John - john@gmail.com
* Subscription("Bmw", 150, 300) - Lara - Lara@gmail.com
*
* Subscription("buy bicycle", 10, 25) - Ben - ben@gmail.com //этот запрос делаем в SQL: like concat('%', :place, '%')
*
* new Ad("buy bmw", 200)
*
* new: Ad,  exists: Subscriptions, Authors, Emails
*
*
* */
