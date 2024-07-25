package com.teamdevroute.devroute.api.visitorcount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class VisitorCountService {

    private final VisitorCountRepository visitorCountRepository;

    public VisitorCountService(VisitorCountRepository visitorCountRepository) {
        this.visitorCountRepository = visitorCountRepository;
    }

    public VisitorCountResponse getVisitorCount() {
        LocalDate today = LocalDate.now();
        log.info("오늘은 " + today.toString() + "입니다.");
        VisitorCount visitorCount = visitorCountRepository.findByVisitDate(today);
        if (visitorCount == null) {
            visitorCount = visitorCountRepository.save(VisitorCount.builder()
                    .visitCount(0L)
                    .visitDate(today)
                    .build()
            );
        }
        return VisitorCountResponse.of(visitorCount);
    }
}
