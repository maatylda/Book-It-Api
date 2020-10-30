package pl.book.it.api.reposietories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
