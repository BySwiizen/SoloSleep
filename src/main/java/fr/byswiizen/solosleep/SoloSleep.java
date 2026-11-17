package fr.byswiizen.solosleep;

import com.tcoded.folialib.FoliaLib;
import dev.dejvokep.boostedyaml.YamlDocument;
import fr.byswiizen.solosleep.command.MainCommand;
import fr.byswiizen.solosleep.command.subcommands.HelpSubCommand;
import fr.byswiizen.solosleep.command.subcommands.ReloadSubCommand;
import fr.byswiizen.solosleep.listener.BedListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import java.io.File;
import java.io.IOException;


public class SoloSleep extends JavaPlugin {
	
	public static YamlDocument configfile, messagesfile;
	private FoliaLib foliaLib;

	
    @Override
    public void onEnable() {
		registerFolia();
		registerMetrics();
		registerFiles();
		registerListener();
		registerCommand();
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
		new Metrics(this, 34949);
	}
	
	private void registerFiles() {
		configfile = loadFile("config.yml");
		messagesfile = loadFile("messages.yml");
	}
	
	private YamlDocument loadFile(String fileName) {
		try {
			 return YamlDocument.create(new File(getDataFolder(), fileName), getResource(fileName));
		} catch (IOException error) {
			throw new RuntimeException(error);
		}
	}
	
	private void registerListener() {
		PluginManager pluginmanager = Bukkit.getServer().getPluginManager();
		pluginmanager.registerEvents(new BedListener(this), this);
	}
	
	private void registerCommand() {
		Lamp<BukkitCommandActor> lamp = BukkitLamp.builder(this).build();
		lamp.register(new MainCommand(this));
		lamp.register(new HelpSubCommand(this));
		lamp.register(new ReloadSubCommand(this));
	}
	
	public FoliaLib getFoliaLib() {
		return foliaLib;
	}
}