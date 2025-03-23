package za.co.awesomatic.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PasswordRule {
    @Id
    @GeneratedValue
    private Long id;
    private String condition;
    private String message;

    public PasswordRule(String condition, String message) {
        this.condition = condition;
        this.message = message;
    }
}
