package com.teamdevroute.devroute.video;

import com.teamdevroute.devroute.video.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository  extends JpaRepository<Videos,Long> {
}
