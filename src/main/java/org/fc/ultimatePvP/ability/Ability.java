package org.fc.ultimatePvP.ability;

public enum Ability {

    SPEED("スピード", "移動速度上昇"),
    STRENGTH("パワー", "攻撃力上昇"),
    TANK("タンク", "最大体力増加"),
    ARCHER("アーチャー", "弓ダメージ上昇"),
    ASSASSIN(
        "アサシン",
                "背後攻撃で1.5倍ダメージ"
    ),
    VAMPIRE(
        "ヴァンパイア",
                "与えたダメージの20%回復"
    ),
    GUARDIAN(
        "ガーディアン",
                "受けるダメージ10%軽減"
    ),
    THUNDER(
        "サンダー",
                "15%の確率で雷を落とし追加ダメージ"
    ),

    PYRO(
        "パイロ",
                "攻撃すると4秒間燃える"
    ),

    REGEN(
            "§a再生",
                    "攻撃を受けると少し回復する"
    );

    private final String displayName;
    private final String description;

    Ability(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}