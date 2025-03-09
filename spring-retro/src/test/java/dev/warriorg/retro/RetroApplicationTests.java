package dev.warriorg.retro;


import java.util.Collection;
import java.util.UUID;

import dev.warriorg.retro.board.Card;
import dev.warriorg.retro.board.CardType;
import dev.warriorg.retro.board.RetroBoard;
import dev.warriorg.retro.service.RetroBoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringBootTest
public class RetroApplicationTests {

    @Autowired
    private RetroBoardService retroBoardService;

    UUID retroBoardUUID = UUID.fromString("9DC9B71B-A07E-418B-B972-40225449AFF2");
    UUID cardUUID = UUID.fromString("BB2A80A5-A0F5-4180-A6DC-80C84BC014C9");
    UUID mehCardUUID = UUID.fromString("775A3905-D6BE-49AB-A3C4-EBE287B51539");

    @Test
    void saveRetroBoardTest() {
        RetroBoard retroBoard = retroBoardService.save(RetroBoard.builder().name("Gathering 2025").build());
        Assertions.assertNotNull(retroBoard);
        Assertions.assertNotNull(retroBoard.getId());
    }

    @Test
    void findAllRetroBoardsTest() {
        Iterable<RetroBoard> all = retroBoardService.findAll();
        Assertions.assertNotNull(all);
        Assertions.assertTrue(all.iterator().hasNext());
    }

    @Test
    void cardsRetroBoardNotFoundTest() {
        assertThatThrownBy(() -> {
            retroBoardService.findAllCardsFromRetroBoard(UUID.randomUUID());
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void findRetroBoardTest() {
        RetroBoard retroBoard = retroBoardService.findById(retroBoardUUID);
        assertThat(retroBoard).isNotNull();
        assertThat(retroBoard.getName()).isEqualTo("Spring Boot3 Meeting");
        assertThat(retroBoard.getId()).isEqualTo(retroBoardUUID);
    }

    @Test
    void findCardsInRetroBoardTest() {
        RetroBoard retroBoard = retroBoardService.findById(retroBoardUUID);
        assertThat(retroBoard).isNotNull();
        assertThat(retroBoard.getCards()).isNotNull();
    }

    @Test
    void addCardToRetroBoardTest() {
        Card card = retroBoardService.addCardToRetroBoard(retroBoardUUID, Card.builder()
                .comment("Amazing session").cardType(CardType.HAPPY)
                .build());

        assertThat(card).isNotNull();
        assertThat(card.getId()).isNotNull();
        RetroBoard retroBoard = retroBoardService.findById(retroBoardUUID);
        assertThat(retroBoard).isNotNull();
        assertThat(retroBoard.getCards()).isNotEmpty();
    }

    @Test
    void findAllCardsFromRetroBoardTest() {
        Iterable<Card> cardList = retroBoardService.findAllCardsFromRetroBoard(retroBoardUUID);
        assertThat(cardList).isNotNull();
        assertThat(((Collection<?>) cardList).size()).isGreaterThan(0);
    }

    @Test
    void removeCardsFromRetroBoardTest() {
        retroBoardService.removeCardFromRetroBoard(retroBoardUUID, cardUUID);
        RetroBoard retroBoard = retroBoardService.findById(retroBoardUUID);
        assertThat(retroBoard).isNotNull();
        assertThat(retroBoard.getCards()).isEmpty();
    }

    @Test
    void findCardByIdInRetroBoardTest() {
        Card card = retroBoardService.findCardByUUIDFromRetroBoard(retroBoardUUID, cardUUID);
        assertThat(card).isNotNull();
        assertThat(card.getId()).isEqualTo(cardUUID);
    }

    @Test
    void findCardByIdInRetroBoardNotFoundTest() {
        assertThatThrownBy(() -> {
            retroBoardService.findCardByUUIDFromRetroBoard(retroBoardUUID, mehCardUUID);
        }).isInstanceOf(RuntimeException.class);
    }

}
