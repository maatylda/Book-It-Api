package pl.book.it.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
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
    private boolean isPaid;

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

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", price=" + price +
                ", isPaid=" + isPaid +
                ", user=" + user +
                ", place=" + place +
                ", room=" + room +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id.equals(booking.id) &&
                dateFrom.equals(booking.dateFrom) &&
                dateTo.equals(booking.dateTo) &&
                Objects.equals(price, booking.price) &&
                user.equals(booking.user) &&
                place.equals(booking.place) &&
                room.equals(booking.room) &&
                createDate.equals(booking.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFrom, dateTo, price, user, place, room, createDate);
    }
}
