package com.codevirtus.membershipsystem.usermanagement.user.service;

import com.codevirtus.membershipsystem.usermanagement.group.dao.GroupDao;
import com.codevirtus.membershipsystem.usermanagement.group.model.Group;
import com.codevirtus.membershipsystem.usermanagement.user.dao.UserDao;
import com.codevirtus.membershipsystem.usermanagement.user.model.Gender;
import com.codevirtus.membershipsystem.usermanagement.user.model.User;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class UserDatabaseInitializer implements InitializingBean {

    private final PasswordEncoder passwordEncoder;

    private final UserDao userDao;

    private final GroupDao groupDao;

    public UserDatabaseInitializer(PasswordEncoder passwordEncoder, UserDao userDao, GroupDao groupDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.groupDao = groupDao;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        val optionalUser = userDao.findByUsername("admin");

        if (optionalUser.isPresent()) {
            return;
        }

        val group = groupDao.findByName("ADMIN").orElseGet(() -> {
            val group1 = new Group();
            group1.setName("ADMIN");

            return groupDao.save(group1);
        });

        val user = new User();
        user.setPassword(passwordEncoder.encode("%$Pass123"));
        user.setUsername("admin");
        user.setFirstName("Wilson");
        user.setLastName("Chiviti");
        user.setEmail("wilson@codevirtus.com");
        user.setTitle("Mr");
        user.setGroup(group);
        user.setEnabled(true);
        user.setGender(Gender.MALE);

        userDao.save(user);

    }

}
