package com.teamdevroute.devroute.visitorcount;

import com.teamdevroute.devroute.api.visitorcount.VisitorCountResponse;
import com.teamdevroute.devroute.api.visitorcount.VisitorCountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class VisitorCountServiceTest {

    @Autowired
    private VisitorCountService visitorCountService;

    @Test
    void update_visitor_count() {
        assertThatThrownBy(() -> visitorCountService.updateVisitorCount())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("오늘자 방문자 수가 없습니다.");
    }

    @Test
    void create_visitor_count() {
        VisitorCountResponse response = visitorCountService.getVisitorCount(0);
        assertThat(response.visitorCount()).isEqualTo(0L);
    }
}
