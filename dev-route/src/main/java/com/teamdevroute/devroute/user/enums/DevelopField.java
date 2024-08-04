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
}
