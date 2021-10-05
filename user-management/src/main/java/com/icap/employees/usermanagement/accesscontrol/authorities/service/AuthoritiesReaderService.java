package com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.service;

import com.codevirtus.membershipsystem.commons.utilities.service.AbstractReaderService;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.dao.AuthorityDao;
import com.codevirtus.membershipsystem.usermanagement.accesscontrol.authorities.model.Authority;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesReaderService extends AbstractReaderService<Authority> {

    public AuthoritiesReaderService(AuthorityDao authorityDao) {
        super(authorityDao);
    }
}
