package dev.warrior.xy.interfaces.jpa;

import dev.warrior.xy.domain.account.Account;
import dev.warrior.xy.domain.account.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<Account, Long>, AccountRepository {

}
