package fr.byswiizen.solosleep.command.subcommands;

import fr.byswiizen.solosleep.SoloSleep;
import fr.byswiizen.solosleep.util.ColorUtil;
import org.bukkit.command.CommandSender;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;


@Command("solosleep")
public class ReloadSubCommand {
	
	public SoloSleep plugin;
	public ReloadSubCommand(SoloSleep instance) {
		this.plugin = instance;
	}
	
	
	@Subcommand("reload")
	@CommandPermission("solosleep.reload")
	public void reload(CommandSender sender) {
		try {
			SoloSleep.configfile.reload();
			SoloSleep.messagesfile.reload();	
			sender.sendMessage(ColorUtil.translate(SoloSleep.messagesfile.getString("prefix") + " " + SoloSleep.messagesfile.getString("reload-success")));
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}