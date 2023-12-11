package me.mxkeeesh.palettifdo;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class PalettiFDO extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.getCommand("palettifdo").setExecutor(this);
        this.getServer().getPluginManager().registerEvents(this, this);
        System.out.println("§8[§9Paletti FDO§8] §9Plguin avviato con successo");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("palettifdo.get")) {
                ItemStack paletti = new ItemStack(Material.ACACIA_FENCE);
                ItemMeta meta = paletti.getItemMeta();
                meta.setDisplayName("§fPaletti FDO");
                paletti.setItemMeta(meta);
                paletti.setAmount(64);
                player.getInventory().addItem(paletti);
            }
        }
        return false;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType().equals(Material.ACACIA_FENCE)) {
            Player p = event.getPlayer();
            if (p.hasPermission("palettifdo.piazza")) {
                event.setCancelled(false);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.ACACIA_FENCE)) {
            Player p = event.getPlayer();
            if (p.hasPermission("palettifdo.piazza")) {
                event.setCancelled(false);
            }
        }
    }
}
