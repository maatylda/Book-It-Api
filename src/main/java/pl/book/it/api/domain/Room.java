package pl.book.it.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.book.it.api.model.room.specifications.RoomStandard;
import pl.book.it.api.model.room.specifications.RoomType;

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

}
