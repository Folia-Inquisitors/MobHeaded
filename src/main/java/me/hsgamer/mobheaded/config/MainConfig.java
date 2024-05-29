package me.hsgamer.mobheaded.config;

import me.hsgamer.hscore.config.annotation.ConfigPath;
import me.hsgamer.mobheaded.config.converter.EntitySkullMapConverter;
import me.hsgamer.mobheaded.data.SkullData;
import me.hsgamer.mobheaded.data.SkullSetting;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.LinkedHashMap;
import java.util.Map;

public interface MainConfig {
    @ConfigPath(value = "mob", converter = EntitySkullMapConverter.class)
    default Map<EntityType, SkullSetting> getMobSettings() {
        Map<EntityType, SkullSetting> map = new LinkedHashMap<>();
        map.put(EntityType.ZOMBIE, new SkullSetting(new SkullData(Material.ZOMBIE_HEAD, 1)));
        map.put(EntityType.SKELETON, new SkullSetting(new SkullData(Material.SKELETON_SKULL, 1)));
        map.put(EntityType.CREEPER, new SkullSetting(new SkullData(Material.CREEPER_HEAD, 1)));
        map.put(EntityType.ENDERMAN, new SkullSetting(new SkullData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E1OWJiMGE3YTMyOTY1YjNkOTBkOGVhZmE4OTlkMTgzNWY0MjQ1MDllYWRkNGU2YjcwOWFkYTUwYjljZiJ9fX0=", 1)));
        return map;
    }

    @ConfigPath({"player", "chance"})
    default double getPlayerChance() {
        return 1;
    }
}
