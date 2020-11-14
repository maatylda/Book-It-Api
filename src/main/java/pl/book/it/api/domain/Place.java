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
@Entity(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne
    private Town town;

    @OneToMany
    @JoinColumn(name = "place_id")
    private List<Room> rooms;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "pictures")
    private List<Picture> pictures;

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
        Place place = (Place) o;
        return id.equals(place.id) &&
                name.equals(place.name) &&
                Objects.equals(phoneNumber, place.phoneNumber) &&
                Objects.equals(street, place.street) &&
                Objects.equals(streetNumber, place.streetNumber) &&
                Objects.equals(zipCode, place.zipCode) &&
                town.equals(place.town) &&
                createDate.equals(place.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, street, streetNumber, zipCode, town, createDate);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", town=" + town +
                ", pictures=" + pictures +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
