package com.dhruv.electronic.store;

import com.dhruv.electronic.store.entities.Role;
import com.dhruv.electronic.store.entities.User;
import com.dhruv.electronic.store.repositories.UserRepository;
import com.dhruv.electronic.store.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@ComponentScan(value = "com.dhruv")
@EnableWebMvc
public class ElectronicStoreApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ElectronicStoreApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository repository;
    @Value("${normal.role.id}")
    private String role_normal_id;
    @Value("${admin.role.id}")
    private String role_admin_id;

    @Autowired
    private UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {

        System.out.println(passwordEncoder.encode("abcd"));

        try {

            Role role_admin = Role.builder().roleId(role_admin_id).roleName("ROLE_ADMIN").build();
            Role role_normal = Role.builder().roleId(role_normal_id).roleName("ROLE_NORMAL").build();


            User adminUser = User.builder()
                    .name("admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .gender("Male")
                    .imageName("default.png")
                    .roles(Set.of(role_admin, role_normal))
                    .userId(UUID.randomUUID().toString())
                    .about("I am admin User")
                    .build();

            User normalUser = User.builder()
                    .name("durgesh")
                    .email("durgesh@gmail.com")
                    .password(passwordEncoder.encode("durgesh123"))
                    .gender("Male")
                    .imageName("default.png")
                    .roles(Set.of(role_normal))
                    .userId(UUID.randomUUID().toString())
                    .about("I am Normal User")
                    .build();

            repository.save(role_admin);
            repository.save(role_normal);


            userRepository.save(adminUser);
            userRepository.save(normalUser);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
