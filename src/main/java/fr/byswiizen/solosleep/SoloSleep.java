package fr.byswiizen.solosleep;

import com.tcoded.folialib.FoliaLib;
import fr.byswiizen.solosleep.listener.BedListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class SoloSleep extends JavaPlugin {
	
	private FoliaLib foliaLib;

	
    @Override
    public void onEnable() {
		registerFolia();
		registerMetrics();
		registerListener();
		getLogger().info("-----------------------");
		getLogger().info(getName() + " v" + getDescription().getVersion());
		getLogger().info("The plugin is enabled.");
		getLogger().info("-----------------------");
    }

    @Override
    public void onDisable() {
		if (foliaLib != null) {
			foliaLib.getScheduler().cancelAllTasks();
		}
		getLogger().info("------------------------");
		getLogger().info(getName() + " v" + getDescription().getVersion());
		getLogger().info("The plugin is disabled.");
		getLogger().info("------------------------");
    }
	
	private void registerFolia() {
		foliaLib = new FoliaLib(this);
	}
	
	private void registerMetrics() {
		new Metrics(this, 11111);
	}
	
	private void registerListener() {
		PluginManager pluginmanager = Bukkit.getServer().getPluginManager();
		pluginmanager.registerEvents(new BedListener(this), this);
	}
	
	public FoliaLib getFoliaLib() {
		return foliaLib;
	}
}