package fr.byswiizen.solosleep.command.subcommands;

import fr.byswiizen.solosleep.SoloSleep;
import fr.byswiizen.solosleep.util.ColorUtil;
import org.bukkit.command.CommandSender;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;


@Command("solosleep")
public class HelpSubCommand {
	
	public SoloSleep plugin;
	public HelpSubCommand(SoloSleep instance) {
		this.plugin = instance;
	}
	
	
	@Subcommand("help")
	public void help(CommandSender sender) {
		for (String line : SoloSleep.messagesfile.getStringList("help")) {
			sender.sendMessage(ColorUtil.translate(SoloSleep.messagesfile.getString("prefix") + " " + line));
		}
	}
}