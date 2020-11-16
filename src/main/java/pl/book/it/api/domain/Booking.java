package pl.book.it.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ToString(exclude = {"user", "place", "room"})
@EqualsAndHashCode(exclude = {"user", "place", "room"})
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_paid")
    private boolean paid;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Place place;

    @JsonIgnore
    @ManyToOne
    private Room room;

    @JsonIgnore
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @JsonIgnore
    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
