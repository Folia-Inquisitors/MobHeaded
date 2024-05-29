package me.hsgamer.mobheaded.data;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Optional;

public class SkullSetting {
    private final SkullData defaultSkull;

    public SkullSetting(SkullData defaultSkull) {
        this.defaultSkull = defaultSkull;
    }

    public static SkullSetting fromMap(Map<String, Object> map) {
        SkullData defaultSkull = SkullData.fromMap(map);
        return new SkullSetting(defaultSkull);
    }

    public Map<String, Object> toMap() {
        return defaultSkull.toMap();
    }

    public Optional<ItemStack> getItem(Entity entity) {
        return defaultSkull.getItem();
    }
}
