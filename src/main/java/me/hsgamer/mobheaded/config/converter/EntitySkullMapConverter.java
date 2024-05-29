package me.hsgamer.mobheaded.config.converter;

import me.hsgamer.hscore.common.MapUtils;
import me.hsgamer.hscore.config.annotation.converter.Converter;
import me.hsgamer.mobheaded.data.SkullSetting;
import org.bukkit.entity.EntityType;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class EntitySkullMapConverter implements Converter {
    @Override
    public Object convert(Object o) {
        if (o instanceof Map) {
            Map<?, ?> rawMap = (Map<?, ?>) o;
            Map<EntityType, SkullSetting> entityMap = new EnumMap<>(EntityType.class);
            for (Map.Entry<?, ?> rawEntry : rawMap.entrySet()) {
                String key = rawEntry.getKey().toString();
                EntityType entityType;
                try {
                    entityType = EntityType.valueOf(key);
                } catch (IllegalArgumentException e) {
                    continue;
                }

                Optional<SkullSetting> skullSetting = MapUtils.castOptionalStringObjectMap(rawEntry.getValue()).map(SkullSetting::fromMap);
                skullSetting.ifPresent(value -> entityMap.put(entityType, value));
            }
            return entityMap;
        }

        return null;
    }

    @Override
    public Object convertToRaw(Object o) {
        if (o instanceof Map) {
            Map<?, ?> entityMap = (Map<?, ?>) o;
            Map<String, Object> rawMap = new LinkedHashMap<>();
            for (Map.Entry<?, ?> entry : entityMap.entrySet()) {
                EntityType entityType = (EntityType) entry.getKey();
                SkullSetting skullSetting = (SkullSetting) entry.getValue();
                rawMap.put(entityType.name(), skullSetting.toMap());
            }
            return rawMap;
        }

        return null;
    }
}
