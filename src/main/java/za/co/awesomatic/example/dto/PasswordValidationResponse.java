package za.co.awesomatic.example.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class PasswordValidationResponse {
    private final boolean valid;
    private final List<String> messages;

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
