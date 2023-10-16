package org.fdryt.ornamental.domain.account;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence", allocationSize = 1)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;
    private boolean enabled = false;
    private boolean credentialsExpired = false;
    private boolean expired = false;
    private boolean locked = false;

    @Enumerated(EnumType.STRING)
    private Role role;
}
