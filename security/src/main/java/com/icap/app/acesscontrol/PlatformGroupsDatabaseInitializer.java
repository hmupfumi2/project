package ccom.icap.app.acesscontrol;

import com.icap.app.usermanagement.group.dao.GroupDao;
import com.icap.app.usermanagement.group.service.GroupsInitializationStrategy;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Configuration
@Slf4j
public class PlatformGroupsDatabaseInitializer extends GroupsInitializationStrategy implements InitializingBean {

    public PlatformGroupsDatabaseInitializer(GroupDao authorityDao) {
        super(authorityDao);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        val groups = Stream.of(SystemGroups.values()).map(Enum::name).collect(toSet());

        persistGroups(groups);

    }

}
