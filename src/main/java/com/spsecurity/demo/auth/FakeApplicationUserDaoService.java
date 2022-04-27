package com.spsecurity.demo.auth;

import com.google.common.collect.Lists;
import com.spsecurity.demo.enumeration.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.spsecurity.demo.enumeration.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUser() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        passwordEncoder.encode("password"),
                        "anna_smith",
                        STUDENT.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        passwordEncoder.encode("password123"),
                        "linda",
                        ADMIN.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        passwordEncoder.encode("password123"),
                        "tom",
                        ADMIN_TRAINEE.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                )
        );
        return applicationUsers;
    }
}
