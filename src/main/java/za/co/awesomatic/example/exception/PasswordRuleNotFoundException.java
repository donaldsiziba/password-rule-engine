package za.co.awesomatic.example.exception;

public class PasswordRuleNotFoundException extends RuntimeException{
    public PasswordRuleNotFoundException(String message) {
        super(message);
    }
}
