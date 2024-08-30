package be.machigan.worldborderexpansion;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldBorderFInder {

    public static Double getDiameter(String worldName, Player player) {
        return
                worldName == null || worldName.isBlank() ?
                getDiameterOfPlayerWorld(player) :
                getDiameterOfWorld(worldName);
    }

    private static Double getDiameterOfWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) return null;
        return world.getWorldBorder().getSize();
    }

    private static Double getDiameterOfPlayerWorld(Player player) {
        return player == null ? null : player.getWorld().getWorldBorder().getSize();
    }

    public static Double getRadius(String worldName, Player player) {
        Double radius = getDiameter(worldName, player);
        return radius == null ? null : radius / 2;
    }
}
