package dev.warriorg.retro.board;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RetroBoard {
    private UUID id;

    @NotNull(message = "A name must be provided") private String name;

    private List<Card> cards;
}
