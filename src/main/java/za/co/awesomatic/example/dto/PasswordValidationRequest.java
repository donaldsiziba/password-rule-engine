package za.co.awesomatic.example.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordValidationRequest {
    private final String password;
}
