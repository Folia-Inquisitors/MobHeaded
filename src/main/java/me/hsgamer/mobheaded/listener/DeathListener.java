package me.hsgamer.mobheaded.listener;

import io.github.projectunified.minelib.plugin.listener.ListenerComponent;
import io.github.projectunified.minelib.scheduler.location.LocationScheduler;
import me.hsgamer.mobheaded.MobHeaded;
import me.hsgamer.mobheaded.config.MainConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Optional;

public class DeathListener implements ListenerComponent {
    private final MobHeaded plugin;

    public DeathListener(MobHeaded plugin) {
        this.plugin = plugin;
    }

    @Override
    public MobHeaded getPlugin() {
        return plugin;
    }

    private void dropItem(Location location, ItemStack itemStack) {
        World world = location.getWorld();
        assert world != null;
        LocationScheduler.get(plugin, location).run(() -> world.dropItemNaturally(location, itemStack));
    }

    private void onEntityDeath(Entity entity) {
        EntityType entityType = entity.getType();
        Optional<ItemStack> optionalItemStack = Optional.ofNullable(plugin.get(MainConfig.class).getMobSettings().get(entityType))
                .flatMap(setting -> setting.getItem(entity));
        if (!optionalItemStack.isPresent()) return;
        ItemStack itemStack = optionalItemStack.get();
        dropItem(entity.getLocation(), itemStack);
    }

    private void onPlayerDeath(Player player) {
        if (Math.random() > plugin.get(MainConfig.class).getPlayerChance()) return;

        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        if (meta == null) return;

        meta.setOwningPlayer(player);
        itemStack.setItemMeta(meta);

        dropItem(player.getLocation(), itemStack);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            onPlayerDeath((Player) entity);
        } else {
            onEntityDeath(entity);
        }
    }
}
