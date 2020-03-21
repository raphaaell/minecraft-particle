package fr.raphael.particule.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import fr.raphael.particule.Main;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		Inventory inv1 = Main.getinfo().inv(4);
		Inventory inv2 = Main.getinfo().inv(1);
		Inventory inv3 = Main.getinfo().inv(2);
		
		if(cmd.getName().equalsIgnoreCase("particle")) {
			
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(args.length == 0 || args[0].equalsIgnoreCase("gui")) {
					p.openInventory(inv1);
				} else if(args[0].equalsIgnoreCase("buy") || args[0].equalsIgnoreCase("marché") || args[0].equalsIgnoreCase("achat")) {
					p.openInventory(inv2);
				} else if(args[0].equalsIgnoreCase("inventaire")) {
					p.openInventory(inv3);
				} else {
					p.sendMessage("Arguments invalide");
				}
			}
			else {
				System.out.println("Cette commande ne peux être éxécuter que par un joueur !");
			}
			
		}
		
		return false;
	}

}