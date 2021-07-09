package dev.wg.xy.domain.account;

public interface AccountRepository {

    /***
     *
     * @param id
     * @return
     */
    boolean existsById(Long id);

    /***
     *
     * @param name
     * @param email
     * @param telephone
     * @return
     */
    boolean existsByUsernameOrEmailOrTelephone(String name, String email, String telephone);
}
