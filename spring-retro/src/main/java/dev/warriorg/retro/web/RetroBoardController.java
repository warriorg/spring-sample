package dev.warriorg.retro.web;

import dev.warriorg.retro.board.Card;
import dev.warriorg.retro.board.RetroBoard;
import dev.warriorg.retro.service.RetroBoardService;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("retros")
public class RetroBoardController {

    private final RetroBoardService retroBoardService;

    public RetroBoardController(RetroBoardService retroBoardService) {
        this.retroBoardService = retroBoardService;
    }

    @GetMapping
    public ResponseEntity<Iterable<RetroBoard>> getAllRetroBoards() {
        return ResponseEntity.ok(this.retroBoardService.findAll());
    }

    @PostMapping
    public ResponseEntity<RetroBoard> saveRetroBoard(RetroBoard retroBoard) {
        RetroBoard result = this.retroBoardService.save(retroBoard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(result.getId().toString())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<RetroBoard> findRetroBoardById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.retroBoardService.findById(uuid));
    }

    @GetMapping("/{uuid}/cards")
    public ResponseEntity<Iterable<Card>> getCardsFromRetroBoard(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.retroBoardService.findAllCardsFromRetroBoard(uuid));
    }

    @PutMapping("{uuid}/cards")
    public ResponseEntity<Card> addCardToRetroBoard(@PathVariable UUID uuid, @RequestBody Card card) {
        Card result = this.retroBoardService.addCardToRetroBoard(uuid, card);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uuid}")
                .buildAndExpand(result.getId().toString())
                .toUri();
        return ResponseEntity.created(location).body(result);
    }

    @GetMapping("{uuid}/cards/{uuidCard}")
    public ResponseEntity<Card> getCardFromRetroBoard(@PathVariable UUID uuid, @PathVariable UUID uuidCard) {
        return ResponseEntity.ok(this.retroBoardService.findCardByUUIDFromRetroBoard(uuid, uuidCard));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{uuid}/cards/{uuidCard}")
    public void deleteCardFromRetroBoard(@PathVariable UUID uuid, @PathVariable UUID uuidCard) {
        this.retroBoardService.removeCardFromRetroBoard(uuid, uuidCard);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("msg", ex.getMessage());
        response.put("code", HttpStatus.NOT_FOUND.value());
        response.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Map<String, Object> errors = new HashMap<>();
        errors.put("msg", ex.getMessage());
        response.put("errors", errors);
        return response;
    }
}
