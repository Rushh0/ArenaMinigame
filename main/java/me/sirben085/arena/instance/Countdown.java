package me.sirben085.arena.instance;

import me.sirben085.arena.GameState;
import me.sirben085.arena.Minigame;
import me.sirben085.arena.manager.ConfigManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Minigame minigame;
    private Arena arena;
    private int countdownSeconds;

    public Countdown(Minigame minigame, Arena arena) {
        this.minigame = minigame;
        this.arena = arena;
        this.countdownSeconds = ConfigManager.getCountdownSeconds();

    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(minigame, 0, 20);
    }

    @Override
    public void run() {
        if (countdownSeconds == 0) {
            cancel();
            arena.start();
            arena.sendTitle("", "");
            return;
        }

        if (countdownSeconds <= 10 || countdownSeconds % 15 == 0) {
            //if countdown seconds = 1, add nothing. If not, add the letter s
            arena.sendMessage(ChatColor.GREEN + "Game will start in " + countdownSeconds + "second" + (countdownSeconds == 1 ? " " : "s") + ".");
        }

        arena.sendTitle(ChatColor.GREEN.toString() + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s"), ChatColor.YELLOW + "until game starts.");

        countdownSeconds--;
    }
}
