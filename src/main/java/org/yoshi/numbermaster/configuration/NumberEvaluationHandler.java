package org.yoshi.numbermaster.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.yoshi.dto.PrimeCheckRequest;
import org.yoshi.dto.PrimeCheckResponse;
import org.yoshi.numbermaster.service.NumberEvaluationService;

@Component
public class NumberEvaluationHandler extends TextWebSocketHandler {
    private final NumberEvaluationService numberEvaluationService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(NumberEvaluationHandler.class.getName());
    public NumberEvaluationHandler(NumberEvaluationService numberEvaluationService,
                                    ObjectMapper objectMapper) {
        this.numberEvaluationService = numberEvaluationService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void handleTextMessage(WebSocketSession wsSession, TextMessage message) throws Exception {
        try {
            PrimeCheckRequest primeRequest = objectMapper.readValue(message.getPayload(), PrimeCheckRequest.class);
            PrimeCheckResponse primeResponse = numberEvaluationService.evaluateNumber(primeRequest);
            String jsonResponse = objectMapper.writeValueAsString(primeResponse);
            wsSession.sendMessage(new TextMessage(jsonResponse));
        } catch (Exception e) {
            logger.error("WS exception while handling a message {} : {}", message.getPayload(), e.getMessage());
            wsSession.sendMessage(new TextMessage("{\"error\":\"Invalid input\"}"));
        }
    }

    @Override
    public void afterConnectionEstablished (WebSocketSession wsSession) {
        logger.info("Client connected to session: {}", wsSession.getId());
    }

    @Override
    public void afterConnectionClosed (WebSocketSession wsSession, CloseStatus closeStatus) {
        logger.info("Client disconnected from session: {}, with status: {}", wsSession.getId(), closeStatus.getReason());
    }
}
