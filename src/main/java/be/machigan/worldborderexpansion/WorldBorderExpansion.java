package be.machigan.worldborderexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class WorldBorderExpansion extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "worldborder";
    }

    @Override
    public String getAuthor() {
        return "Machigan";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        Double result = null;
        if (params.startsWith("radius")) {
            result =  WorldBorderFInder.getRadius(
                    getWorldNameInParams(params.replace("radius", "")),
                    player
            );
        }
        else if (params.startsWith("diameter")) {
            result =  WorldBorderFInder.getDiameter(
                    getWorldNameInParams(params.replace("diameter", "")),
                    player
            );
        }
        return result == null ? "" : beautifyPlaceholderResult(result, params);
    }



    @Override
    public String onRequest(OfflinePlayer p, String params) {
        if (p == null)
            return this.onPlaceholderRequest(null, params);
        return this.onPlaceholderRequest(Bukkit.getPlayer(p.getUniqueId()), params);
    }

    private static String getWorldNameInParams(String params) {
        params = params
                .replace("_bn", "")
                .replace("_sn", "")
                ;
        return
                params.startsWith("_") ?
                new StringBuilder(params).deleteCharAt(0).toString()
                : null;
    }

    private static String beautifyPlaceholderResult(double placeholderResult, String originalParams) {
        if (originalParams.endsWith("bn"))
            return Tools.betterNumber(placeholderResult);
        if (originalParams.endsWith("sn"))
            return Tools.shortNumber(placeholderResult);
        return String.valueOf(placeholderResult);
    }
}
