package za.co.awesomatic.example.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import za.co.awesomatic.example.dao.PasswordRuleRepository;
import za.co.awesomatic.example.dto.PasswordValidationRequest;
import za.co.awesomatic.example.dto.PasswordValidationResponse;
import za.co.awesomatic.example.entity.PasswordRule;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PasswordValidator implements Function<PasswordValidationRequest, PasswordValidationResponse> {
    private final PasswordRuleRepository repository;
    private final ExpressionParser parser = new SpelExpressionParser();
    private final Map<String, Expression> expressionCache = new ConcurrentHashMap<>();
    private final StandardEvaluationContext context = new StandardEvaluationContext();

    @Override
    public PasswordValidationResponse apply(PasswordValidationRequest request) {
        context.setVariable("password", request.getPassword());

        List<String> messages = repository.findAll().stream()
                .filter(rule -> !isPasswordValid(rule))
                .map(PasswordRule::getMessage)
                .toList();
        return new PasswordValidationResponse(messages.isEmpty(), messages);
    }

    private boolean isPasswordValid(PasswordRule rule) {
        Expression expression = expressionCache.computeIfAbsent(
                rule.getCondition(), parser::parseExpression);

        return Boolean.TRUE.equals(expression.getValue(context, Boolean.class));
    }
}
