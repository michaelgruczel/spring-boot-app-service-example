package de.mgr.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("UserData %s does not exist!", username));
        }
        return new UserRepositoryUserDetails(user);
    }

    private static final class LoggedInUserAuthority implements GrantedAuthority {

        public String getAuthority() {
            return "USER";
        }
    }


    private static final class UserRepositoryUserDetails implements UserDetails {

        private static final long serialVersionUID = 1L;

        private User user;

        private UserRepositoryUserDetails(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            // all uses have role user, no concept for admin... make sense
            return Arrays.asList(new LoggedInUserAuthority());
            //return getRoles();
        }

        @Override
        public String getUsername() {
            return user.getMail();
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }

}
