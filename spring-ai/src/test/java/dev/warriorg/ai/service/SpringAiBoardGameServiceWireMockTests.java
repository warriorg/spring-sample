package dev.warriorg.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import dev.warriorg.ai.dto.Answer;
import dev.warriorg.ai.dto.Question;
import dev.warriorg.ai.service.impl.SpringAiBoardGameService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.evaluation.RelevancyEvaluator;
import org.springframework.ai.evaluation.EvaluationRequest;
import org.springframework.ai.evaluation.EvaluationResponse;
import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.io.IOException;
import java.nio.charset.Charset;

@EnableWireMock(@ConfigureWireMock(baseUrlProperties = "openai.base.url"))
@SpringBootTest(
        properties = "spring.ai.openai.base-url=${openai.base.url}")
public class SpringAiBoardGameServiceWireMockTests {

    @Value("classpath:/test-openai-response.json")
    Resource responseResource;

    @Autowired
    ChatClient.Builder chatClientBuilder;

    @Autowired
    private BoardGameService boardGameService;

    private RelevancyEvaluator relevancyEvaluator;

    @BeforeEach
    public void setup() throws IOException {
        this.relevancyEvaluator = new RelevancyEvaluator(chatClientBuilder);
        var cannedResponse =
                responseResource.getContentAsString(Charset.defaultCharset());
        var mapper = new ObjectMapper();
        var responseNode = mapper.readTree(cannedResponse);
        WireMock.stubFor(WireMock.post("/v1/chat/completions")
                .willReturn(ResponseDefinitionBuilder.okForJson(responseNode)));
    }

    @Test
    public void evaluateRelevancy() {
       String userText = "Why is the sky blue?";
       Question question = new Question(userText);
       Answer answer = this.boardGameService.askQuestion(question);
       EvaluationRequest evaluationRequest = new EvaluationRequest(userText, answer.answer());
       EvaluationResponse response = relevancyEvaluator.evaluate(evaluationRequest);
       Assertions.assertThat(response.isPass())
               .withFailMessage("""
                  ========================================
                  The answer "%s"
                  is not considered relevant to the question
                  "%s".
                  ========================================
                  """, answer.answer(), userText)
               .isTrue();;

    }

    @Test
    public void testAskQuestion() {
        var boardGameService =
                new SpringAiBoardGameService(chatClientBuilder);
        var answer =
                boardGameService.askQuestion(
                        new Question("What is the capital of France?"));
        Assertions.assertThat(answer).isNotNull();
        Assertions.assertThat(answer.answer()).isEqualTo("Paris");
    }
}
