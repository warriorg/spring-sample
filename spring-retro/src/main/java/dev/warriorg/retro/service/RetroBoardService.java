package dev.warriorg.retro.service;

import dev.warriorg.retro.board.Card;
import dev.warriorg.retro.board.RetroBoard;
import dev.warriorg.retro.exception.CardNotFoundException;
import dev.warriorg.retro.persistence.BaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RetroBoardService {

    private final BaseRepository<RetroBoard, UUID> repository;

    public RetroBoardService(BaseRepository<RetroBoard, UUID> repository) {
        this.repository = repository;
    }

    public RetroBoard save(RetroBoard domain) {
        if (domain.getCards() == null) {
            domain.setCards(new ArrayList<>());
        }
        return this.repository.save(domain);
    }

    public RetroBoard findById(UUID uuid) {
        // NullPointerException is handled through AOP
        return this.repository.findById(uuid).get();
    }

    public Iterable<RetroBoard> findAll() {
        return this.repository.findAll();
    }

    public void delete(UUID uuid) {
        this.repository.delete(uuid);
    }

    public Iterable<Card> findAllCardsFromRetroBoard(UUID uuid) {
        return this.findById(uuid).getCards();
    }

    public Card addCardToRetroBoard(UUID uuid, Card card) {
        if (card.getId() == null) {
            card.setId(UUID.randomUUID());
        }
        RetroBoard retroBoard = this.findById(uuid);
        List<Card> cardList = new ArrayList<>(retroBoard.getCards());
        cardList.add(card);
        retroBoard.setCards(cardList);
        return card;
    }

    public Card findCardByUUIDFromRetroBoard(UUID uuid, UUID uuidCard) {
        return this.findById(uuid).getCards().stream()
                .filter(card -> card.getId().equals(uuidCard))
                .findFirst()
                .orElseThrow(() -> new CardNotFoundException());
    }

    public void removeCardFromRetroBoard(UUID uuid, UUID uuidCard) {
        RetroBoard retroBoard = this.findById(uuid);
        List<Card> cardList = new ArrayList<>(retroBoard.getCards());
        cardList.removeIf(card -> card.getId().equals(uuidCard));
        retroBoard.setCards(cardList);
    }
}
