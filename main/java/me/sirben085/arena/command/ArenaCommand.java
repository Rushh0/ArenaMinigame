package me.sirben085.arena.command;

import me.sirben085.arena.GameState;
import me.sirben085.arena.Minigame;
import me.sirben085.arena.instance.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    private Minigame minigame;

    public ArenaCommand (Minigame minigame) {
        this.minigame = minigame;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                player.sendMessage(ChatColor.GREEN + "These are the availible arenas: ");

                for (Arena arena : minigame.getArenaManager().getArenas()) {
                    player.sendMessage(ChatColor.GREEN + "- " + arena.getId() + " sta(" + arena.getState().name() + ")");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                Arena arena = minigame.getArenaManager().getArena(player);

                if (arena != null) {
                    player.sendMessage(ChatColor.RED + "You left the arena ");
                    arena.removePlayer(player);
                } else {
                    player.sendMessage(ChatColor.RED + "You are not in an arena!");
                }

            } else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
                if (minigame.getArenaManager().getArena(player) != null) {
                    player.sendMessage(ChatColor.RED +  "You are already in an arena!");

                    return false;
                }

                int id;

                    try {
                        id = Integer.parseInt(args[1]);

                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "You specified an invalid arena ID!");
                        return false;
                    }

                    if (id >= 0 && id < minigame.getArenaManager().getArenas().size()) {

                        Arena arena = minigame.getArenaManager().getArena(id);
                        if (arena.getState() == GameState.RECRUITING || arena.getState() == GameState.RECRUITING) {
                            player.sendMessage(ChatColor.GREEN + "You are now playing in Arena " + id + ".");
                            arena.addPlayer(player);
                        } else {
                            player.sendMessage(ChatColor.RED + "You are not playing in an arena right now!");
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "You specified an invalid arena ID!");
                    }

            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Options below:");
                player.sendMessage(ChatColor.RED + "/arena list");
                player.sendMessage(ChatColor.RED + "/arena leave");
                player.sendMessage(ChatColor.RED + "/arena join");

            }
        }

        return false;
    }
}
