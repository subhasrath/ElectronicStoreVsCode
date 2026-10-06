package com.subhas.ElectronicStore;

import com.subhas.ElectronicStore.entity.Role;
import com.subhas.ElectronicStore.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class ElectronicStoreApplication {
    private static final Logger log = LoggerFactory.getLogger(ElectronicStoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ElectronicStoreApplication.class, args);
    }
    @Bean
    CommandLineRunner seedRoles(RoleRepository roleRepository,
                                @Value("${admin.role.id}") String adminRoleId,
                                @Value("${normal.role.id}") String normalRoleId) {
        return args -> {
            createRoleIfMissing(roleRepository, adminRoleId, "ROLE_ADMIN");
            createRoleIfMissing(roleRepository, normalRoleId, "ROLE_NORMAL");
        };
    }

    private void createRoleIfMissing(RoleRepository repo, String roleId, String roleName) {
        if (repo.findById(roleId).isEmpty()) {
            Role role = Role.builder()
                    .roleId(roleId)
                    .roleName(roleName)
                    .build();
            repo.save(role);
            log.info("Seeded role: {} ({})", roleName, roleId);
        } else {
            log.info("Role already present, skipping: {} ({})", roleName, roleId);
        }
    }

}