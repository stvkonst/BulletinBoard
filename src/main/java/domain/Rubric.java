package domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rubric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rubric_id")
    int id;

    String name;
}
