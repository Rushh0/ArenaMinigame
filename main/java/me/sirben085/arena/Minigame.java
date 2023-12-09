package me.sirben085.arena;

import me.sirben085.arena.command.ArenaCommand;
import me.sirben085.arena.listener.ConnectListener;
import me.sirben085.arena.listener.GameListener;
import me.sirben085.arena.manager.ArenaManager;
import me.sirben085.arena.manager.ConfigManager;
import me.sirben085.arena.manager.LangManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minigame extends JavaPlugin {

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
        LangManager.setupLangFile(this);

        arenaManager = new ArenaManager(this);

        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);

        getCommand("arena").setExecutor(new ArenaCommand(this));


    }

    public ArenaManager getArenaManager() { return arenaManager; }
}
