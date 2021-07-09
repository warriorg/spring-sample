package dev.wg.xy.domain.account;

import dev.wg.xy.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_account")
@EqualsAndHashCode(callSuper=true)
public class Account extends BaseEntity {

    private String username;
    private String email;
    private String telephone;
    private String address;
    private String password;
}
