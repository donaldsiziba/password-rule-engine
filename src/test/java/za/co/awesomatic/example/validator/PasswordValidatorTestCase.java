package za.co.awesomatic.example.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.awesomatic.example.dto.PasswordValidationRequest;
import za.co.awesomatic.example.dto.PasswordValidationResponse;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PasswordValidatorTestCase {
    @Autowired
    PasswordValidator passwordValidator;

    @ParameterizedTest(name = "Validate Password: {0}")
    @MethodSource("provideTestData")
    void validatePassword(String password, boolean valid, String[] messages) {
        PasswordValidationResponse result = passwordValidator.apply(
                new PasswordValidationRequest(password)
        );

        assertEquals(valid, result.isValid());
        assertArrayEquals(messages, result.getMessages().toArray());
    }

    // Data provider method
    static Stream<org.junit.jupiter.params.provider.Arguments> provideTestData() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        "userp@ssw0rd", false, new String[]{"The password should have at least one uppercase character."}
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "USERP@SSW0RD", false, new String[]{"The password should have at least one lowercase character."}
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "UserP@ssword", false, new String[]{"The password should have at least one digit."}
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "UserPassw0rd", false, new String[]{"The password should have at least one special character."}
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "UserP@sw0rd", false, new String[]{"The password should be at least 12 characters long."}
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "User   P@ss w0rd", false, new String[]{"The password should not have any whitespaces."}
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "UserP@ssw0rD", true, new String[]{}
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "userpasword", false, new String[]{
                                "The password should have at least one uppercase character.",
                                "The password should have at least one digit.",
                                "The password should have at least one special character.",
                                "The password should be at least 12 characters long."
                        }
                )
        );
    }
}
