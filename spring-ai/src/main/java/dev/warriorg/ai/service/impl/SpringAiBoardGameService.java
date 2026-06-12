package dev.warriorg.ai.service.impl;

import dev.warriorg.ai.dto.Answer;
import dev.warriorg.ai.dto.Question;
import dev.warriorg.ai.service.BoardGameService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SpringAiBoardGameService implements BoardGameService {

    private final ChatClient chatClient;

    public SpringAiBoardGameService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public Answer askQuestion(Question question) {
        var answerText = chatClient.prompt()
                .user(question.question())
                .call().content();

        return new Answer(answerText);
    }
}
