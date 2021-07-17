package dev.wg.xy.domain.account;

import dev.wg.xy.infrastructure.CrudRepository;

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
