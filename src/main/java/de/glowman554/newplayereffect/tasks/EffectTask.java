package de.glowman554.newplayereffect.tasks;

import de.glowman554.newplayereffect.NewPlayerEffect;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EffectTask extends BukkitRunnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getStatistic(Statistic.PLAY_ONE_MINUTE) < NewPlayerEffect.getInstance().getNewPlayerTimeMinutes() * 60 * 20 && NewPlayerEffect.getInstance().getWorlds().contains(player.getWorld().getName())) {
                NewPlayerEffect.getInstance().getEffects().forEach(e -> e.apply(player));
            }
        }
    }
}
