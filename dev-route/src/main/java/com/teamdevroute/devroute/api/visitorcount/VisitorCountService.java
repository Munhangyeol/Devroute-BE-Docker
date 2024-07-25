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

    public VisitorCountResponse getVisitorCount(int type) {
        LocalDate today = LocalDate.now();
        LocalDate yearAgo = today.minusYears(1);
        LocalDate monthAgo = today.minusMonths(1);
        LocalDate weekAgo = today.minusWeeks(1);
        LocalDate dayAgo = today.minusDays(1);

        log.info("오늘은 " + today.toString() + "입니다.");

        initTodayCount();

        Long visitcount = 0L;

        switch (type) {
            case 0:
                visitcount = visitorCountRepository.findByVisitDateBetween(dayAgo, today);
                break;
            case 1:
                visitcount = visitorCountRepository.findByVisitDateBetween(weekAgo, today);
                break;
            case 2:
                visitcount= visitorCountRepository.findByVisitDateBetween(monthAgo, today);
                break;
            default:
                visitcount = visitorCountRepository.findByVisitDateBetween(yearAgo, today);
                break;
        }

        return VisitorCountResponse.builder()
                .visitorCount(visitcount)
                .build();
    }

    private void initTodayCount() {
        LocalDate today = LocalDate.now();
        VisitorCount visitorCount = visitorCountRepository.findByVisitDate(today);
        if (visitorCount == null) {
            visitorCountRepository.save(VisitorCount.builder()
                    .visitCount(0L)
                    .visitDate(today)
                    .build()
            );
        }
    }
}
