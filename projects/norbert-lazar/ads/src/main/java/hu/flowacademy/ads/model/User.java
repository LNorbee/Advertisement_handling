package hu.flowacademy.ads.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "user_entity")
public class User {

    @Id
    @Column(name = "user_name", unique = true)
    private String userName;

    private String fullName;

    @CreatedDate
    private LocalDate creationDate;

    @OneToMany(mappedBy = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Ad> ads;

    public User(String userName, String fullName, LocalDate creationDate) {
        this.userName = userName;
        this.fullName = fullName;
        this.creationDate = creationDate;
    }
}
