package pl.book.it.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Place place;

    @Column(name = "path")
    private String path;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
