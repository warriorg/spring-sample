package dev.warriorg.ai.service;

import dev.warriorg.ai.dto.Answer;
import dev.warriorg.ai.dto.Question;

public interface BoardGameService {
    Answer askQuestion(Question question);
}
