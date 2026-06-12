package dev.warriorg.retro.advice;

import dev.warriorg.retro.board.RetroBoard;
import dev.warriorg.retro.exception.RetroBoardNotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.Optional;
import java.util.UUID;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RetroBoardAdvice {
    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Around("execution(* dev.warriorg.retro.persistence.RetroBoardRepository.findById(java.util.UUID))")
    public Object checkFindRetroBoard(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("[ADVICE] findRetroBoardById");
        Optional<RetroBoard> retroBoard = (Optional<RetroBoard>) proceedingJoinPoint.proceed(
                new Object[] {UUID.fromString(proceedingJoinPoint.getArgs()[0].toString())});
        if (retroBoard.isEmpty()) {
            throw new RetroBoardNotFoundException();
        }
        return retroBoard;
    }
}
