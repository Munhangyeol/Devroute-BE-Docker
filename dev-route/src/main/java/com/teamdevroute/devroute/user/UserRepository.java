package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
