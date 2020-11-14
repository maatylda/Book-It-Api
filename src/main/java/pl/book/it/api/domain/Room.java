package pl.book.it.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.book.it.api.model.room.specifications.RoomStandard;
import pl.book.it.api.model.room.specifications.RoomType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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
    @Enumerated(value = EnumType.STRING)
    private RoomStandard standard;

    @JsonIgnore
    @Column(name = "room_type")
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;

    @JsonIgnore
    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @ManyToOne
    private Place place;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Booking> bookings;

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
        Room room = (Room) o;
        return id.equals(room.id) &&
                standard == room.standard &&
                roomType == room.roomType &&
                place.equals(room.place) &&
                createDate.equals(room.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, standard, roomType, place, createDate);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", standard=" + standard +
                ", roomType=" + roomType +
                ", price=" + price +
                ", place=" + place +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
