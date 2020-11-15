package pl.book.it.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return id.equals(town.id) &&
                name.equals(town.name) &&
                createDate.equals(town.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createDate);
    }
}
