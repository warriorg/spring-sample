package dev.warrior.xy.domain.account;

import dev.warrior.xy.infrastructure.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    /***
     *
     * @param name
     * @param email
     * @param telephone
     * @return
     */
    boolean existsByUsernameOrEmailOrTelephone(String name, String email, String telephone);
}
