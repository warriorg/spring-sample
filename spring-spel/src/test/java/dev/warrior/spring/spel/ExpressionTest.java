package dev.warrior.spring.spel;

import dev.warrior.spring.spel.vo.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author gao shiyong
 * @since 2022/12/20 09:34
 */
public class ExpressionTest {

    @Test
    public void giveModel_whenCompute_thenValue() {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("model");

        Car car = new Car();
        car.setMake("Good manufacturer");
        car.setModel("Model 3");
        car.setHorsePower(2014);

        EvaluationContext context = new StandardEvaluationContext(car);
        System.out.println(expression.getValue(context));
    }

    @Test
    @DisplayName("模版测试")
    public void giveTemplate_whenCompute_thenValue() {
        ExpressionParser parser = new SpelExpressionParser();
        String randomPhrase = parser.parseExpression("random number is #{T(java.lang.Math).random()}",
                        new TemplateParserContext()).getValue(String.class);
        System.out.println(randomPhrase);
    }

}
