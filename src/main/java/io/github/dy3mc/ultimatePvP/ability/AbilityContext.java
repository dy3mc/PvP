package io.github.dy3mc.ultimatePvP.ability;

import io.github.dy3mc.ultimatePvP.UltimatePvP;

import java.util.Random;

public class AbilityContext {

    private final UltimatePvP plugin;
    private final Random random;
    private final AbilityUtil util;
    private final CooldownManager cooldown;
    private final AbilityEffect effect;

    public AbilityContext(UltimatePvP plugin) {

        this.plugin = plugin;
        this.random = new Random();
        this.util = new AbilityUtil(plugin);
        this.cooldown = new CooldownManager();
        this.effect = new AbilityEffect(this);

    }

    public UltimatePvP getPlugin() {
        return plugin;
    }

    public Random getRandom() {
        return random;
    }

    public AbilityUtil getUtil() {
        return util;
    }

    public CooldownManager getCooldown() {
        return cooldown;
    }

    public AbilityEffect getEffect() {
        return effect;
    }

}