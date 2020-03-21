package fr.raphael.particule;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import fr.raphael.particule.commands.Commands;
import fr.raphael.particule.listeners.ParticleListeners;

public class Main extends JavaPlugin implements Listener{
	
	private static Main instance;
	private static Info info = new Info();
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdf = getDescription();
		instance = this;
		String etat = "ON";
		System.out.println("[" + pdf.getName() + "] " + etat + " (Version : " + pdf.getVersion() + ")");
		getCommand("particle").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new ParticleListeners(), this);
		
	}
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdf = getDescription();
		String etat = "OFF";
		System.out.println("[" + pdf.getName() + "] " + etat + " Version : " + pdf.getVersion() + "]");
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static Info getinfo() {
		return info;
	}

	
}