package com.teamdevroute.devroute.api.visitorcount;

import lombok.Builder;

import java.time.LocalDate;

public record VisitorCountResponse(
        Long visitorCount,
        LocalDate visitDate
) {

    @Builder
    public VisitorCountResponse(Long visitorCount, LocalDate visitDate) {
        this.visitorCount = visitorCount;
        this.visitDate = visitDate;
    }
}
