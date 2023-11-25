package me.sirben085.arena.listener;

import me.sirben085.arena.GameState;
import me.sirben085.arena.Minigame;
import me.sirben085.arena.instance.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameListener implements Listener {

    private Minigame minigame;

    public GameListener(Minigame minigame) {
        this.minigame = minigame;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Arena arena = minigame.getArenaManager().getArena(e.getPlayer());

            if (arena.getState().equals(GameState.LIVE) && arena != null) {
                arena.getGame().addPoint(e.getPlayer());
            }
        }
    }

