package org.fdryt.ornamental.domain.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
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

    @JsonProperty(access = WRITE_ONLY)
    private String password;

    private boolean enabled = true;
    private boolean credentialsNonExpired = true;
    private boolean nonExpired = true;
    private boolean nonLocked = true;

    @Enumerated(EnumType.STRING)
    private Role role;
}
