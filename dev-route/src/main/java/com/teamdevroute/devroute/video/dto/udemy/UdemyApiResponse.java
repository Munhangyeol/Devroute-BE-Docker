package com.teamdevroute.devroute.video.dto.udemy;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UdemyApiResponse {
    private List<Course> results;

    // getters and setters

    public static class Course {
        private String title;
        private String url;
        private String price;
        private String image_125_H;

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getPrice() {
            return price;
        }

        public String getImage_125_H() {
            return image_125_H;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setImage_125_H(String image_125_H) {
            this.image_125_H = image_125_H;
        }

        // getters and setters
    }
}