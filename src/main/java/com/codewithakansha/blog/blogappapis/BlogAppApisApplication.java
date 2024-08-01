package com.codewithakansha.blog.blogappapis;

import com.codewithakansha.blog.blogappapis.configs.AppConstants;
import com.codewithakansha.blog.blogappapis.entities.Role;
import com.codewithakansha.blog.blogappapis.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) {
        System.out.println(this.passwordEncoder.encode("testing"));

        try {
            Role role = new Role();
            role.setId(AppConstants.ADMIN);
            role.setName("ADMIN_USER");

            Role role1 = new Role();
            role1.setId(AppConstants.NORMAL_USER);
            role1.setName("NORMAL_USER");

            List<Role> roles = List.of(role, role1);
            this.roleRepo.saveAll(roles);

            roles.forEach(r -> { System.out.println(r.getName());
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
