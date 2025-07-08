package za.co.awesomatic.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.awesomatic.example.dao.PasswordRuleRepository;
import za.co.awesomatic.example.entity.PasswordRule;
import za.co.awesomatic.example.exception.PasswordRuleNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rules")
public class PasswordRuleEngineController {
    final PasswordRuleRepository repository;

    @GetMapping("/{ruleId}")
    public PasswordRule getRuleById(@PathVariable Long ruleId) {
        return repository.findById(ruleId).orElseThrow(() -> new PasswordRuleNotFoundException(String.format("Password rule not found: id=%d", ruleId)));
    }
}
