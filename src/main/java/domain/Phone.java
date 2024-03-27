package domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "author")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    int id;

    String name;

    @ManyToOne
    @JoinColumn(name = "FK_Phone_Author")
    Author author;
}
