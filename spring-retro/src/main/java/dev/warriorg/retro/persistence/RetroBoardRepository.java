package dev.warriorg.retro.persistence;

import dev.warriorg.retro.board.Card;
import dev.warriorg.retro.board.CardType;
import dev.warriorg.retro.board.RetroBoard;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class RetroBoardRepository implements BaseRepository<RetroBoard, UUID> {
    private Map<UUID, RetroBoard> retroBoardMap = new HashMap<>() {
        {
            put(
                    UUID.fromString("9DC9B71B-A07E-418B-B972-40225449AFF2"),
                    RetroBoard.builder()
                            .id(UUID.fromString("9DC9B71B-A07E-418B-B972-40225449AFF2"))
                            .name("Spring Boot3 Meeting")
                            .cards(List.of(Card.builder()
                                    .id(UUID.fromString("BB2A80A5-A0F5-4180-A6DC-80C84BC014C9"))
                                    .comment("Happy to meet the team")
                                    .cardType(CardType.HAPPY)
                                    .build()))
                            .build());
        }
    };

    @Override
    public RetroBoard save(RetroBoard domain) {
        if (domain.getId() == null) {
            domain.setId(UUID.randomUUID());
        }
        this.retroBoardMap.put(domain.getId(), domain);
        return domain;
    }

    @Override
    public Optional<RetroBoard> findById(UUID uuid) {
        return Optional.ofNullable(this.retroBoardMap.get(uuid));
    }

    @Override
    public Iterable<RetroBoard> findAll() {
        return this.retroBoardMap.values();
    }

    @Override
    public void delete(UUID uuid) {
        this.retroBoardMap.remove(uuid);
    }
}
