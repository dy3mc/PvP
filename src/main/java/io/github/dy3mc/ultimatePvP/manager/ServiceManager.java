package io.github.dy3mc.ultimatePvP.manager;

import io.github.dy3mc.ultimatePvP.UltimatePvP;
import io.github.dy3mc.ultimatePvP.ability.AbilityContext;
import io.github.dy3mc.ultimatePvP.ability.AbilityManager;
import io.github.dy3mc.ultimatePvP.ability.AbilityRegistry;
import io.github.dy3mc.ultimatePvP.ability.*;

public class ServiceManager {

    private final AbilityContext abilityContext;

    private final AbilityRegistry abilityRegistry;
    private final AbilityManager abilityManager;

    private final RoundManager roundManager;

    private final KillStreakManager killStreakManager;
    private final BountyManager bountyManager;

    private final AwakenManager awakenManager;
    private final GameManager gameManager;

    private final ActionBarManager actionBarManager;

    public ServiceManager(UltimatePvP plugin) {

        abilityContext = new AbilityContext(plugin);

        abilityRegistry = new AbilityRegistry(abilityContext);

        abilityManager = new AbilityManager(
                abilityContext,
                abilityRegistry
        );

        roundManager = new RoundManager(
                plugin,
                abilityManager
        );

        killStreakManager = new KillStreakManager();

        bountyManager = new BountyManager();

        awakenManager = new AwakenManager();

        gameManager = new GameManager(roundManager);

        actionBarManager = new ActionBarManager(plugin);

    }

    public AbilityContext getAbilityContext() {
        return abilityContext;
    }

    public AbilityRegistry getAbilityRegistry() {
        return abilityRegistry;
    }

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }

    public RoundManager getRoundManager() {
        return roundManager;
    }

    public KillStreakManager getKillStreakManager() {
        return killStreakManager;
    }

    public BountyManager getBountyManager() {
        return bountyManager;
    }

    public AwakenManager getAwakenManager() {
        return awakenManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ActionBarManager getActionBarManager() {
        return actionBarManager;
    }
}