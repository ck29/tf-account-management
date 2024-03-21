package com.assignment.tf.repositories;

import com.assignment.tf.repositories.entities.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

  Optional<AccountEntity> findByEmail(String email);
  Optional<AccountEntity> findByIban(String iban);

}
