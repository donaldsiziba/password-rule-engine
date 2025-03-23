package za.co.awesomatic.example

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PasswordRulesApplicationTests extends Specification {

    def "spring application context loads successfully"() {
        expect:
            true
    }
}
