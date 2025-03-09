package dev.warriorg.retro.board;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RetroBoard {
    private UUID id;

    @NotNull(message = "A name must be provided")
    private String name;

    private List<Card> cards;

}
