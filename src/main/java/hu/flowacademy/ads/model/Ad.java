package hu.flowacademy.ads.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "id", allocationSize = 1)
    private Long id;

    private String title;

    private Integer price;

    private String description;

    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "userName_fk", nullable = false)
    @JsonIgnore
    private User user;

    public Ad(String title, Integer price, String description, User user, LocalDate creationDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.creationDate = creationDate;
        this.user = user;
    }

}
