package pl.book.it.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.book.it.api.domain.specifications.MaxNumberOfGuestsForRoom;
import pl.book.it.api.domain.specifications.RoomStandard;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "standard")
    private RoomStandard standard;

    @Column(name = "number_of_guests")
    private MaxNumberOfGuestsForRoom numberOfGuests;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    private Pleace pleace;

    @OneToMany
    @JoinColumn(name = "room_id")
    private Set<Booking> bookings;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
