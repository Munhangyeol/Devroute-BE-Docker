package com.teamdevroute.devroute.user.enums;

public enum DevelopField {
    BACKEND,
    FRONTEND,
    MOBILE,
    AI,
    DATA_SCIENCE,
    NONE;

    public static DevelopField toEnum(String type) {
        return switch (type) {
            case "backend" -> DevelopField.BACKEND;
            case "frontend" -> DevelopField.FRONTEND;
            case "ai" -> DevelopField.AI;
            case "mobile" -> DevelopField.MOBILE;
            case "datascience" -> DevelopField.DATA_SCIENCE;
            default -> DevelopField.NONE;
        };
    }

    public static DevelopField toEnumBySearchKeyWord(String type) {
        return switch (type) {
            case "백엔드" -> DevelopField.BACKEND;
            case "프론트엔드" -> DevelopField.FRONTEND;
            case "인공지능" -> DevelopField.AI;
            case "모바일" -> DevelopField.MOBILE;
            case "데이터" -> DevelopField.DATA_SCIENCE;
            default -> DevelopField.NONE;
        };
    }
}
