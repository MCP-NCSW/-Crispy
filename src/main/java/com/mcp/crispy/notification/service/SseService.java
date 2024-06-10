package com.mcp.crispy.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SseService {

    private final Map<Long, SseEmitter> emitters = new HashMap<>();
    public SseEmitter createEmitter(Long empNo) {
        log.info("호출됐나요?");
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(empNo, emitter);
        emitter.onCompletion(() -> emitters.remove(empNo));
        emitter.onTimeout(() -> emitters.remove(empNo));
        emitter.onError((e) -> emitters.remove(empNo));

        // Heartbeat 전송
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(15000); // 15초마다 heartbeat 전송
                    emitter.send(SseEmitter.event().name("heartbeat").data("heartbeat"));
                }
            } catch (Exception e) {
                emitters.remove(empNo);
            }
        }).start();

        return emitter;
    }

    public void sendNotification(Long empNo, String message) {
        SseEmitter emitter = emitters.get(empNo);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("notification").data(message));
                log.info("전송됐나요?");
            } catch (Exception e) {
                emitters.remove(empNo);
            }
        }
    }
}
