package za.co.awesomatic.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.awesomatic.example.dao.PasswordRuleRepository;
import za.co.awesomatic.example.entity.PasswordRule;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PasswordRuleRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.saveAll(Arrays.asList(
                new PasswordRule("#password.matches('.*[A-Z].*')", "The password should have at least one uppercase character."),
                new PasswordRule("#password.matches('.*[a-z].*')", "The password should have at least one lowercase character."),
                new PasswordRule("#password.matches('.*[0-9].*')", "The password should have at least one digit."),
                new PasswordRule("#password.matches('.*[^a-zA-Z0-9].*')", "The password should have at least one special character."),
                new PasswordRule("#password.length() >= 12", "The password should be at least 12 characters long."),
                new PasswordRule("#password.matches('\\S+$')", "The password should not have any whitespaces.")
        ));
    }
}
