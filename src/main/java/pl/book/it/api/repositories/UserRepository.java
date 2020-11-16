package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
