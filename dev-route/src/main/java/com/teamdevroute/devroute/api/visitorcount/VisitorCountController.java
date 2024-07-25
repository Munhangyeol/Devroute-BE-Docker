package com.teamdevroute.devroute.api.visitorcount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class VisitorCountController {

    private VisitorCountService visitorCountService;

    public VisitorCountController(VisitorCountService visitorCountService) {
        this.visitorCountService = visitorCountService;
    }

    @GetMapping("/main/visitor")
    public ResponseEntity<VisitorCountResponse> getVisitorCount() {
        VisitorCountResponse response = visitorCountService.getVisitorCount();
        return ResponseEntity.ok().body(response);
    }
}
