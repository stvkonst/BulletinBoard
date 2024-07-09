package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate publicationDate;

    BigDecimal price;

    @Column(name = "ad_text")
    String text;

    @Column(name = "is_active")
    @JsonProperty(value = "isActive")
    boolean active;

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
