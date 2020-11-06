package pl.book.it.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.book.it.api.domain.specifications.RoomStandard;
import pl.book.it.api.domain.specifications.RoomType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "standard")
    private RoomStandard standard;

    @JsonIgnore
    @Column(name = "number_of_guests")
    private RoomType numberOfGuests;

    @JsonIgnore
    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @ManyToOne
    private Place place;

    @JsonIgnore
    @OneToMany
    @JoinTable(name = "bookings_in_rooms")
    private List<Booking> bookings;

    @JsonIgnore
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @JsonIgnore
    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

}
