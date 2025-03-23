package za.co.awesomatic.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.awesomatic.example.entity.PasswordRule;

public interface PasswordRuleRepository extends JpaRepository<PasswordRule, Long> {
}
