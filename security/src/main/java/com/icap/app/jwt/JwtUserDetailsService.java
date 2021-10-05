package ccom.icap.app.jwt;

import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.model.Authority;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.groupauthorities.model.GroupAuthority;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.groupauthorities.service.GroupAuthorityService;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.userauthorities.model.UserAuthority;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.userauthorities.service.UserAuthorityService;
import com.codevirtus.membershipsystem.usermanagement.user.dao.UserDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;
import lombok.val;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    private final GroupAuthorityService groupAuthorityService;

    private final UserAuthorityService userAuthorityService;

    public JwtUserDetailsService(UserDao userDao, GroupAuthorityService groupAuthorityService,
                                 UserAuthorityService userAuthorityService) {
        this.userDao = userDao;
        this.groupAuthorityService = groupAuthorityService;
        this.userAuthorityService = userAuthorityService;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        val userOptional = userDao.findByUsername(username);

        if (userOptional.isPresent()) {

            val user = userOptional.get();

            val groupAuthorities = groupAuthorityService.findByGroup(user.getGroup().getId());
            groupAuthorities.stream().map(GroupAuthority::getAuthority)
                    .map(Authority::getName)
                    .map(SimpleGrantedAuthority::new)
                    .forEach(user::addAuthority);

            val userAuthorities = userAuthorityService.findByUser(user.getId());
            userAuthorities.stream().map(UserAuthority::getAuthority)
                    .map(Authority::getName)
                    .map(SimpleGrantedAuthority::new)
                    .forEach(user::addAuthority);

            user.addAuthority(new SimpleGrantedAuthority("ROLE_" + user.getGroup().getName().toUpperCase()));

            return user;

        }

        throw new UsernameNotFoundException("User record not found");
    }
}
