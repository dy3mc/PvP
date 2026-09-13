package org.fc.ultimatePvP;

import org.bukkit.plugin.java.JavaPlugin;
import org.fc.ultimatePvP.ability.AbilityContext;
import org.fc.ultimatePvP.ability.AbilityManager;
import org.fc.ultimatePvP.ability.AbilityRegistry;
import org.fc.ultimatePvP.command.AbilityCommand;
import org.fc.ultimatePvP.command.DevCommand;
import org.fc.ultimatePvP.command.PvPCommand;
import org.fc.ultimatePvP.listener.*;
import org.fc.ultimatePvP.manager.*;

public final class UltimatePvP extends JavaPlugin {

    private AbilityManager abilityManager;
    private AbilityRegistry abilityRegistry;

    private RoundManager roundManager;
    private KillStreakManager killStreakManager;
    private BountyManager bountyManager;
    private AwakenManager awakenManager;
    private GameManager gameManager;

    private ScoreboardManager scoreboardManager;
    private ActionBarManager actionBarManager;
    private AbilityContext abilityContext;
    private GameRuleManager gameRuleManager;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        abilityContext = new AbilityContext(this);

        abilityRegistry = new AbilityRegistry(abilityContext);

        abilityManager = new AbilityManager(
                abilityContext,
                abilityRegistry
        );

        roundManager = new RoundManager(this, abilityManager);
        gameRuleManager = new GameRuleManager(roundManager);

        roundManager.setGameRuleManager(gameRuleManager);

        killStreakManager = new KillStreakManager();
        bountyManager = new BountyManager();
        awakenManager = new AwakenManager();

        gameManager = new GameManager(roundManager);

        actionBarManager = new ActionBarManager(this);

        getCommand("pvp").setExecutor(
                new PvPCommand(roundManager)
        );

        getCommand("ability").setExecutor(
                new AbilityCommand(abilityManager)
        );

        getCommand("dev").setExecutor(
                new DevCommand(
                        abilityManager,
                        killStreakManager,
                        bountyManager,
                        awakenManager,
                        roundManager,
                        this
                )
        );

        getLogger().info("UltimatePvP Enabled");

        getServer().getPluginManager().registerEvents(
                new DamageListener(roundManager),
                this
        );

        getServer().getPluginManager().registerEvents(
                new GameRuleListener(roundManager),
                this
        );

        getServer().getPluginManager().registerEvents(
                new AbilityMoveListener(
                        abilityManager,
                        abilityRegistry
                ),
                this
        );

        getServer().getPluginManager().registerEvents(
                new DeathListener(
                        killStreakManager,
                        bountyManager,
                        awakenManager,
                        gameManager
                ),
                this
        );

        getServer().getPluginManager().registerEvents(
                new AwakenListener(awakenManager),
                this
        );

        getServer().getPluginManager().registerEvents(
                new AbilityDamageListener(
                        abilityManager,
                        abilityRegistry
                ),
                this
        );

        getServer().getPluginManager().registerEvents(
                new AbilityInteractListener(
                        abilityManager,
                        abilityRegistry
                ),
                this
        );

        getServer().getPluginManager().registerEvents(
                new AbilityGUIListener(abilityManager),
                this
        );

        scoreboardManager = new ScoreboardManager(
                this,
                abilityManager,
                killStreakManager,
                bountyManager,
                awakenManager,
                actionBarManager
        );

        scoreboardManager.start();
    }

    @Override
    public void onDisable() {

    }

    public boolean isDeveloperMode() {
        return getConfig().getBoolean("developer-mode");
    }

}