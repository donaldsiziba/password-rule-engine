package za.co.awesomatic.example.validator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll
import za.co.awesomatic.example.dto.PasswordValidationRequest
import za.co.awesomatic.example.dto.PasswordValidationResponse

@SpringBootTest
class PasswordValidatorSpecification extends Specification {
    
    @Autowired
    PasswordValidator passwordValidator

    @Unroll
    def "validate the following password - #password"() {
        given: "the following password: #password"

        when: "the password is validated"
            PasswordValidationResponse result = passwordValidator.apply(new PasswordValidationRequest(password))

        then: "the result should be #valid"
            result.valid == valid

        and: "the following message should be returned to the user: #messages"
            result.messages == messages

        where:
            password           || valid | messages
            'userp@ssw0rd'     || false | ['The password should have at least one uppercase character.']
            'USERP@SSW0RD'     || false | ['The password should have at least one lowercase character.']
            'UserP@ssword'     || false | ['The password should have at least one digit.']
            'UserPassw0rd'     || false | ['The password should have at least one special character.']
            'UserP@sw0rd'      || false | ['The password should be at least 12 characters long.']
            'User   P@ss w0rd' || false | ['The password should not have any whitespaces.']
            'UserP@ssw0rD'     || true  | []
            'userpasword'      || false | ['The password should have at least one uppercase character.','The password should have at least one digit.','The password should have at least one special character.','The password should be at least 12 characters long.']
    }
}
