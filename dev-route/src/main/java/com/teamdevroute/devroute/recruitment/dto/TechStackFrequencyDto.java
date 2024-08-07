package com.teamdevroute.devroute.recruitment.dto;

public record TechStackFrequencyDto(
        String techStack,
        int count,
        double percentage
) {

    public static TechStackFrequencyDto of(String techStack, int count, int totalCount) {
        if (totalCount == 0) {
            throw new IllegalArgumentException("기술 스택 빈도가 0일 수 없습니다");
        }

        double percentage = Math.round(((double) count / totalCount) * 1000) / 10.0;
        return new TechStackFrequencyDto(techStack, count, percentage);
    }
}
