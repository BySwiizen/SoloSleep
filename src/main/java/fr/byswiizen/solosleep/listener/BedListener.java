package fr.byswiizen.solosleep.listener;

import fr.byswiizen.solosleep.SoloSleep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import java.util.concurrent.TimeUnit;


public class BedListener implements Listener {
	
	public SoloSleep plugin;
	public BedListener(SoloSleep instance) {
		this.plugin = instance;
	}
	
	
	@EventHandler
	public void onBed(PlayerBedEnterEvent event) {
		plugin.getFoliaLib().getScheduler().runAtEntityLater(event.getPlayer(), () -> {
			if (event.getPlayer().isSleeping()) {
				plugin.getFoliaLib().getScheduler().runNextTick((_ -> {
					event.getPlayer().getWorld().setTime(0L);
					event.getPlayer().getWorld().setStorm(false);
					event.getPlayer().getWorld().setThundering(false);
				}));
			}
		}, 5L, TimeUnit.SECONDS);
	}
}