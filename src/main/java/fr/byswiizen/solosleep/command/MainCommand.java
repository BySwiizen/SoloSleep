package fr.byswiizen.solosleep.command;

import fr.byswiizen.solosleep.SoloSleep;
import fr.byswiizen.solosleep.command.subcommands.HelpSubCommand;
import org.bukkit.command.CommandSender;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.CommandPlaceholder;


@Command("solosleep")
public class MainCommand {
	
	public SoloSleep plugin;
	public HelpSubCommand helpcommand;
	public MainCommand(SoloSleep instance) {
		this.plugin = instance;
		this.helpcommand = new HelpSubCommand(instance);
	}
	
	
	@CommandPlaceholder
	public void help(CommandSender sender) {
		helpcommand.help(sender);
	}
}