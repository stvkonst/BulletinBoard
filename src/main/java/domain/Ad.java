package domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    int id;

    String name;

    @Column(name = "publication_date")
    LocalDate publicationDate;

    long price;

    @Column(name = "ad_text")
    String text;

    @ManyToOne
    @JoinColumn(name = "FK_Ad_Author")
    Author author;

    @ManyToOne
    @JoinColumn(name = "FK_Ad_Rubric")
    Rubric rubric;
}

/*
* Ad -> FK_Author
* Ad -> FK_Rubric
* */
