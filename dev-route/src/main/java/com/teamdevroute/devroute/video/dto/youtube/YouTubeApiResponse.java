package com.teamdevroute.devroute.video.dto.youtube;

import java.util.List;
public class YouTubeApiResponse {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private Id id;
        private Snippet snippet;

        public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public static class Id {
            private String videoId;

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }
        }

        public static class Snippet {
            private String title;
            private Thumbnails thumbnails;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(Thumbnails thumbnails) {
                this.thumbnails = thumbnails;
            }
        }

        public static class Thumbnails {
            private Thumbnail defaultThumbnail;

            public Thumbnail getDefault() {
                return defaultThumbnail;
            }

            public void setDefault(Thumbnail defaultThumbnail) {
                this.defaultThumbnail = defaultThumbnail;
            }

            public static class Thumbnail {
                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}