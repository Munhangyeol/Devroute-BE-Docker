package com.teamdevroute.devroute.video;

import com.teamdevroute.devroute.video.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Videos,Long> {
    List<Videos> findByPlatformNameAndTeckStack(String platform_name, String teck_stack);
}
