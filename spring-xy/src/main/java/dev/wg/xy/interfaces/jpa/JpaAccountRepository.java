package dev.wg.xy.interfaces.jpa;

import dev.wg.xy.domain.account.Account;
import dev.wg.xy.domain.account.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<Account, Long>, AccountRepository {

}
