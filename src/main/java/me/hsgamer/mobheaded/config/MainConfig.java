package me.hsgamer.mobheaded.config;

import me.hsgamer.hscore.config.annotation.ConfigPath;
import me.hsgamer.mobheaded.config.converter.EntitySkullMapConverter;
import me.hsgamer.mobheaded.data.SkullData;
import me.hsgamer.mobheaded.data.SkullSetting;
import org.bukkit.entity.EntityType;

import java.util.LinkedHashMap;
import java.util.Map;

public interface MainConfig {
    @ConfigPath(value = "mob", converter = EntitySkullMapConverter.class)
    default Map<EntityType, SkullSetting> getMobSettings() {
        Map<EntityType, SkullSetting> map = new LinkedHashMap<>();
        map.put(EntityType.ZOMBIE, new SkullSetting(new SkullData("MHF_Zombie", 1)));
        map.put(EntityType.SKELETON, new SkullSetting(new SkullData("MHF_Skeleton", 1)));
        map.put(EntityType.CREEPER, new SkullSetting(new SkullData("MHF_Creeper", 1)));
        map.put(EntityType.ENDERMAN, new SkullSetting(new SkullData("MHF_Enderman", 1)));
        return map;
    }

    @ConfigPath({"player", "chance"})
    default double getPlayerChance() {
        return 1;
    }
}
