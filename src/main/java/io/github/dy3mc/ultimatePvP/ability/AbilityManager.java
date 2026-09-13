package io.github.dy3mc.ultimatePvP.ability;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class AbilityManager {

    private final HashMap<UUID, Ability> abilities =
            new HashMap<>();

    private final AbilityContext context;
    private final AbilityRegistry registry;

    public AbilityManager(
            AbilityContext context,
            AbilityRegistry registry
    ) {

        this.context = context;
        this.registry = registry;

    }

    /**
     * ランダムな能力を付与
     */
    public void giveRandomAbility(Player player) {

        Ability[] values = Ability.values();

        Ability ability =
                values[
                        context.getRandom().nextInt(
                                values.length
                        )
                        ];

        setAbility(player, ability);

    }

    /**
     * 能力を設定
     *
     * すでに能力を持っている場合は、
     * 古い能力を解除してから新しい能力を付与する。
     */
    public void setAbility(
            Player player,
            Ability ability
    ) {

        /*
         * 現在の能力
         */
        Ability oldAbility =
                abilities.get(player.getUniqueId());

        /*
         * 同じ能力なら何もしない
         */
        if (oldAbility == ability)
            return;

        /*
         * 古い能力を解除
         */
        if (oldAbility != null) {

            AbilityHandler oldHandler =
                    registry.getHandler(oldAbility);

            if (oldHandler != null) {

                oldHandler.onRemove(player);

            }
        }

        /*
         * 能力変更時にクールダウンを完全解除
         *
         * これによって、
         *
         * Thunder
         * ↓
         * Assassin
         *
         * のように変更した場合でも、
         * 前の能力のクールダウンが残らない。
         */
        context.getCooldown().clearPlayer(player);

        /*
         * 新しい能力を保存
         */
        abilities.put(
                player.getUniqueId(),
                ability
        );

        /*
         * 新しい能力の付与処理
         */
        AbilityHandler handler =
                registry.getHandler(ability);

        if (handler != null) {

            handler.onGive(player);

        }

        /*
         * プレイヤーへ通知
         */
        player.sendMessage(
                "§6あなたの能力: §e"
                        + ability.getDisplayName()
        );

        player.sendMessage(
                "§7"
                        + ability.getDescription()
        );

    }

    /**
     * 現在の能力を取得
     */
    public Ability getAbility(Player player) {

        return abilities.get(
                player.getUniqueId()
        );

    }

    /**
     * 能力を削除
     */
    public void removeAbility(Player player) {

        UUID uuid =
                player.getUniqueId();

        Ability ability =
                abilities.remove(uuid);

        if (ability == null) {

            /*
             * 能力がなくても、
             * 念のためクールダウンを解除
             */
            context.getCooldown().clearPlayer(player);

            return;
        }

        AbilityHandler handler =
                registry.getHandler(ability);

        if (handler != null) {

            handler.onRemove(player);

        }

        /*
         * 能力削除時にクールダウンも解除
         */
        context.getCooldown().clearPlayer(player);

    }

    /**
     * 全プレイヤーへランダム能力を付与
     */
    public void giveRandomAbilityToAll(
            Collection<? extends Player> players
    ) {

        for (Player player : players) {

            giveRandomAbility(player);

        }

    }

    /**
     * 全プレイヤーの能力を解除
     */
    public void removeAllAbilities(
            Collection<? extends Player> players
    ) {

        for (Player player : players) {

            removeAbility(player);

        }

    }

}