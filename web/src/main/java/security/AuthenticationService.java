package security;


import com.restaurant.dao.pojos.User;
import com.restaurant.dao.pojos.UserDTO;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.IUserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("authenticationService")
public class AuthenticationService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger(AuthenticationService.class);


    private final IUserService userService;

    @Autowired
    public AuthenticationService(IUserService userService) {
        this.userService = userService;
    }

    @Transactional (readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user;
        UserDTO userDTO;
        try {
            user = userService.getUserByLogin(login);
            if (user == null) throw new UsernameNotFoundException("User not found");
            userDTO = new UserDTO (user.getId(),
                    user.getUsername(),
                    user.getUserpassword(),
                    true,
                    true,
                    true,
                    true,
                    setAuthorities(user.getAccess()));
        }
        catch (ServiceException e) {
            throw new UsernameNotFoundException("User not found");
        }
        return userDTO;
    }

    private Collection<GrantedAuthority> setAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (role.equals("ADMIN")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }
}
