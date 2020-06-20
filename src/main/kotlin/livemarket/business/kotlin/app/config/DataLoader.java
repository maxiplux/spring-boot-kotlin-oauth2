package livemarket.business.kotlin.app.config;


import livemarket.business.kotlin.app.models.Role;
import livemarket.business.kotlin.app.models.RoleName;
import livemarket.business.kotlin.app.models.User;
import livemarket.business.kotlin.app.repository.RoleRepository;
import livemarket.business.kotlin.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;




    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.createUser();

    }

    private void createUser() {


        if (!this.userRepository.existsByUsername("admin"))
        {
            User user = new User.Builder()
            .username("admin")
            .enabled(true)
            .roles(this.roleRepository.findAll().stream().collect(Collectors.toSet()) )
                    .password(passwordEncoder().encode("12345")).build();

            roleRepository.save(new  Role.Builder().name(RoleName.ROLE_USER.name()).build());
            roleRepository.save(new  Role.Builder().name(RoleName.ROLE_ADMIN.name()).build());
            roleRepository.save(new Role.Builder().name(RoleName.ROLE_MODERATOR.name()).build());


            this.userRepository.save(user);

        }



    }

}
