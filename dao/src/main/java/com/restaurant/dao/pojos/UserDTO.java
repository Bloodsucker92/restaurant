package com.restaurant.dao.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private Collection<GrantedAuthority> authorities;

}

