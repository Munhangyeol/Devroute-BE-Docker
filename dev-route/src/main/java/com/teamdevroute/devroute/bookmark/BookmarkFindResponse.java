package com.teamdevroute.devroute.bookmark;

import com.teamdevroute.devroute.bookmark.domain.BookmarkCompany;
import com.teamdevroute.devroute.bookmark.domain.BookmarkRoadmap;
import com.teamdevroute.devroute.bookmark.domain.BookmarkTech;
import com.teamdevroute.devroute.bookmark.domain.BookmarkVideo;
import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookmarkFindResponse {
    private List<BookmarkCompany> companies;
    private List<BookmarkRoadmap> roadmaps;
    private List<BookmarkTech> teches;
    private List<BookmarkVideo> videos;

    @Builder
    public BookmarkFindResponse(List<BookmarkCompany> companies, List<BookmarkRoadmap> roadmaps, List<BookmarkTech> teches, List<BookmarkVideo> videos) {
        this.companies = companies;
        this.roadmaps = roadmaps;
        this.teches = teches;
        this.videos = videos;
    }
}
