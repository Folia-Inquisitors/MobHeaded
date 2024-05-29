package me.hsgamer.mobheaded;

import io.github.projectunified.minelib.plugin.base.BasePlugin;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.proxy.ConfigGenerator;
import me.hsgamer.mobheaded.config.MainConfig;
import me.hsgamer.mobheaded.listener.DeathListener;

import java.util.Arrays;
import java.util.List;

public final class MobHeaded extends BasePlugin {
    @Override
    protected List<Object> getComponents() {
        return Arrays.asList(
                ConfigGenerator.newInstance(MainConfig.class, new BukkitConfig(this)),
                new DeathListener(this)
        );
    }
}
