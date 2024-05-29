package me.hsgamer.mobheaded.data;

import com.cryptomorin.xseries.SkullUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class SkullData {
    private final String skull;
    private final double chance;

    public SkullData(String skull, double chance) {
        this.skull = skull;
        this.chance = chance;
    }

    public SkullData(Material material, double chance) {
        this("ITEM:" + material.name(), chance);
    }

    public static SkullData fromMap(Map<String, Object> map) {
        String skull = Optional.ofNullable(map.get("skull")).map(Object::toString).orElse("Steve");
        double chance = Optional.ofNullable(map.get("chance")).map(Object::toString).map(Double::parseDouble).orElse(0.0);
        return new SkullData(skull, chance);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("skull", skull);
        map.put("chance", chance);
        return map;
    }

    public String getSkull() {
        return skull;
    }

    public double getChance() {
        return chance;
    }

    public Optional<ItemStack> getItem() {
        if (Math.random() > chance) {
            return Optional.empty();
        }

        ItemStack item;
        if (skull.startsWith("ITEM:")) {
            String materialString = skull.substring(5);
            Material material = Material.matchMaterial(materialString);
            if (material == null || !material.isItem()) {
                return Optional.empty();
            }

            item = new ItemStack(material);
        } else {
            item = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            if (meta == null) {
                return Optional.empty();
            }

            meta = SkullUtils.applySkin(meta, skull);
            item.setItemMeta(meta);
        }

        return Optional.of(item);
    }
}
