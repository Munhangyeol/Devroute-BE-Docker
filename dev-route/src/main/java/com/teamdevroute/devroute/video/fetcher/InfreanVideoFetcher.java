package com.teamdevroute.devroute.video.fetcher;

import com.teamdevroute.devroute.crawling.InfreanVideoCrawling;
import com.teamdevroute.devroute.video.dto.infrean.InfreanVideoDTO;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class InfreanVideoFetcher {
    private final InfreanVideoCrawling infreanVideoCrawling;

    public InfreanVideoFetcher(InfreanVideoCrawling infreanVideoCrawling) {
        this.infreanVideoCrawling = infreanVideoCrawling;
    }

    public ArrayList<InfreanVideoDTO> fetchInfreanVideos(TechnologyStackName value) throws IOException {
        return infreanVideoCrawling.crawlingInfreanVideo(value);
    }
}
