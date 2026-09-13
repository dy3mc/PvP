package org.fc.ultimatePvP.manager;

public enum GameRule {

    SPEED("§bスピードラウンド"),
    LOW_GRAVITY("§a低重力"),
    DOUBLE_DAMAGE("§cダメージ2倍"),
    NO_RULE("§7通常ルール");

    private final String displayName;

    GameRule(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}