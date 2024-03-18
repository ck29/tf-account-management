package com.assignment.tf.repositories;

import com.assignment.tf.repositories.entities.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

  Optional<AccountEntity> findByEmail(String email);

}
