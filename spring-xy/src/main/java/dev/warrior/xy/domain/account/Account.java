package dev.warrior.xy.domain.account;

import dev.warrior.xy.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_account")
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

    private String username;
    private String email;
    private String telephone;
    private String address;
    private String password;
}
