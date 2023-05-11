package me.heyxmirko.pvpprotectionbedrockfix;

import de.netzkronehd.wgregionevents.events.RegionEnterEvent;
import nl.marido.deluxecombat.api.DeluxeCombatAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class PvpProtectionBedrockFix extends JavaPlugin implements Listener {

    DeluxeCombatAPI deluxeCombatAPI = new DeluxeCombatAPI();
    Location teleportLocation;


    @EventHandler
    public void onRegionEnter(RegionEnterEvent event) {
        if(event.getRegion().getId().equals("warzone_pvp")) {
            Player player = event.getPlayer();
            Boolean hasProtection =  deluxeCombatAPI.hasProtection(player);
            if(hasProtection) {
                player.teleport(teleportLocation);
                player.sendMessage(ChatColor.RED+"You cannot enter the Warzone because you have active PVP protection. You can disable it using "+ChatColor.GREEN+"/pvpprotection disable");
            }
        }
    }



    @Override
    public void onEnable() {
        teleportLocation = new Location(Bukkit.getWorld("world"), 0.5, 77, -73.5, 180, 0);
        getLogger().info("Plugin has been loaded!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin has been unloaded!");
    }
}
