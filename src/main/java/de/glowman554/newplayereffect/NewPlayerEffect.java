package de.glowman554.newplayereffect;

import de.glowman554.newplayereffect.tasks.EffectTask;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

public final class NewPlayerEffect extends JavaPlugin {
    private static NewPlayerEffect instance;

    public NewPlayerEffect() {
        instance = this;
    }

    private final ArrayList<ApplyingEffect> effects = new ArrayList<>();
    private final ArrayList<String> worlds = new ArrayList<>();
    private int newPlayerTimeMinutes = 0;

    private final FileConfiguration config = getConfig();

    @Override
    public void onLoad() {
        config.addDefault("effects", new String[]{"minecraft:regeneration@1", "minecraft:resistance@2"});
        config.addDefault("newPlayerTimeMinutes", 5 * 60);

        config.addDefault("applyingWorlds", new String[]{"world"});

        config.options().copyDefaults(true);
        saveConfig();
        reloadConfig();

        for (Object entry : Objects.requireNonNull(config.getList("effects"))) {
            if (entry instanceof String string) {
                ApplyingEffect effect = ApplyingEffect.parse(string);
                getLogger().log(Level.INFO, effect.toString());
                effects.add(effect);
            }
        }

        for (Object entry : Objects.requireNonNull(config.getList("applyingWorlds"))) {
            if (entry instanceof String string) {
                worlds.add(string);
            }
        }

        newPlayerTimeMinutes = config.getInt("newPlayerTimeMinutes");
    }

    @Override
    public void onEnable() {
        new EffectTask().runTaskTimer(this, 0, 20 * 30);
    }

    @Override
    public void onDisable() {
    }

    public ArrayList<ApplyingEffect> getEffects() {
        return effects;
    }

    public ArrayList<String> getWorlds() {
        return worlds;
    }

    public int getNewPlayerTimeMinutes() {
        return newPlayerTimeMinutes;
    }

    public static NewPlayerEffect getInstance() {
        return instance;
    }
}
