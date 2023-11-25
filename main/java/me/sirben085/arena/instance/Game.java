package me.sirben085.arena.instance;

import me.sirben085.arena.GameState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena) {
        this.arena = arena;
        points = new HashMap<>();
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.GREEN + "Game has started! Objective: Break 200 blocks in tge fastest time.");

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }


    }

    public void addPoint(Player player) {
        int playerPoints = points.get(player.getUniqueId()) + 1;

        if (playerPoints == 20) {
            arena.sendMessage(ChatColor.GOLD + player.getName() + "has won! Thanks for playing.");
            arena.reset(true);
            return;
        }

        player.sendMessage(ChatColor.GREEN + "+1 Point!");
        points.replace(player.getUniqueId(), playerPoints);
    }
}
