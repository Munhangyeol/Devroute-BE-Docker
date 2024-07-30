package com.teamdevroute.devroute.api.visitorcount;

import lombok.Builder;

import java.time.LocalDate;

public record VisitorCountResponse(
        Long visitorCount
) {

    @Builder
    public VisitorCountResponse(Long visitorCount) {
        this.visitorCount = visitorCount;
    }
}
