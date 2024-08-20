package com.as_pasa.htsk_2.repositories;

import com.as_pasa.htsk_2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
