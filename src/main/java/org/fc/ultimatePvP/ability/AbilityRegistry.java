package org.fc.ultimatePvP.ability;

import org.fc.ultimatePvP.ability.abilities.*;

import java.util.EnumMap;
import java.util.Map;

public class AbilityRegistry {

    private final Map<Ability, AbilityHandler> handlers =
            new EnumMap<>(Ability.class);

    public AbilityRegistry(AbilityContext context) {

        register(
                Ability.SPEED,
                new SpeedAbility(context)
        );

        register(
                Ability.THUNDER,
                new ThunderAbility(context)
        );

        register(
                Ability.PYRO,
                new PyroAbility(context)
        );

        register(
                Ability.VAMPIRE,
                new VampireAbility(context)
        );

        register(
                Ability.GUARDIAN,
                new GuardianAbility(context)
        );

        register(
                Ability.ASSASSIN,
                new AssassinAbility(context)
        );

        register(
                Ability.REGEN,
                new RegenAbility(context)
        );

    }

    /**
     * 能力を登録
     */
    private void register(
            Ability ability,
            AbilityHandler handler
    ) {

        handlers.put(
                ability,
                handler
        );

    }

    /**
     * 能力に対応するHandlerを取得
     */
    public AbilityHandler getHandler(
            Ability ability
    ) {

        return handlers.get(ability);

    }

    /**
     * 能力が登録されているか確認
     */
    public boolean hasHandler(
            Ability ability
    ) {

        return handlers.containsKey(ability);

    }

}