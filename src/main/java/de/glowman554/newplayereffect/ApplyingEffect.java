package de.glowman554.newplayereffect;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public record ApplyingEffect(PotionEffectType type, int level) {
    public void apply(Player player) {
        player.addPotionEffect(new PotionEffect(type, 20 * 60, level - 1, true, true, true));
    }

    public static ApplyingEffect parse(String in) {
        String[] split = in.split("@");
        PotionEffectType type = PotionEffectType.getByKey(NamespacedKey.fromString(split[0]));
        int level = Integer.parseInt(split[1]);
        return new ApplyingEffect(type, level);
    }
}
