package com.icap.app.acesscontrol;

import com.icap.app.usermanagement.accesscontrol.authorities.dao.AuthorityDao;
import ccom.icap.app.usermanagement.accesscontrol.authorities.service.AuthoritiesInitializationStrategy;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Configuration
@Slf4j
public class PlatformAuthoritiesDatabaseInitializer extends AuthoritiesInitializationStrategy implements InitializingBean {

    public PlatformAuthoritiesDatabaseInitializer(AuthorityDao authorityDao) {
        super(authorityDao);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        val authorities = Stream.of(PlatformAuthorities.values()).map(Enum::name).collect(toSet());

        persistAuthorities(authorities);

    }

}
