package pl.book.it.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinColumn(name = "town_id")
    private List<Place> places;

    @JsonIgnore
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @JsonIgnore
    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

}
