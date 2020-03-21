package fr.raphael.particule.listeners;

import org.bukkit.Bukkit;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import fr.raphael.particule.Info;
import fr.raphael.particule.Main;




public class ParticleListeners implements Listener {
	
	public int argent = 800;
	int prixflame = 200;
	int prixem = 200;
	int choix = 0;
	int choixhelix = 0;
	public boolean achatem = false;
	boolean achatflame = false;
	Info inf = Main.getinfo();
	Inventory inv1 = inf.inv(1);
	Inventory inv2 = inf.inv(2);
	Inventory inv3 = inf.inv(3);
	Inventory inv4 = inf.inv(4);
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		InventoryView inv = e.getView();
		Player p = (Player) e.getWhoClicked();
		ItemStack current = e.getCurrentItem();
		
		if(current == null) return;
		
		switch(inv.getTitle()) {
			case "§eMenu":
				e.setCancelled(true);
				switch(current.getType()) {
					case DIAMOND_BLOCK:
						if(current.getItemMeta().getDisplayName().equals("§cMarché")) {
							p.openInventory(inv1);
						}
						break;
					case CHEST:
						if(current.getItemMeta().getDisplayName().equals("§2Inventaire")) {
							p.openInventory(inv2);
						}
						break;
						
					default:
						break;
				}
				break;
			case "§cMarché":
				e.setCancelled(true);
				switch(current.getType()) {
					case FLINT_AND_STEEL:
						if(current.getItemMeta().getDisplayName().equals("§cFLAME")) {
							if(this.argent >= prixflame) {
								choix = 1;
								p.openInventory(inv3);
							}
						}
						break;
					case EMERALD:
						if(current.getItemMeta().getDisplayName().equals("§aEMERALD")) {
							if(this.argent >= prixem) {
								choix = 2;
								p.openInventory(inv3);
							}
						}
						break;
					case BOOK:
						if(current.getItemMeta().getDisplayName().equals("§eMenu")) {
							p.openInventory(inv4);
						}
						break;
					default:
						break;
				}
				break;
			case "§2Inventaire":
				e.setCancelled(true);
				switch(current.getType()) {
					case FLINT_AND_STEEL:
						if(current.getItemMeta().getDisplayName().equals("§cFLAME")) {
							this.choixhelix = 1;
							createHelix(p);
							p.closeInventory();
						}
						break;
					case EMERALD:
						if(current.getItemMeta().getDisplayName().equals("§aEMERALD")) {
							this.choixhelix = 2;
							createHelix(p);
							p.closeInventory();
						}
						break;
					case BOOK:
						if(current.getItemMeta().getDisplayName().equals("§eMenu")) {
							p.openInventory(inv4);
						}
						break;
					default:
						break;
				}
				break;
			case "§8Confirmation":
				e.setCancelled(true);
				switch(current.getType()) {
					case GREEN_WOOL:
						if(current.getItemMeta().getDisplayName().equals("�2Achetez la particle ?")) {
							if(choix == 1) {
								this.achatflame = true;
								this.argent -= prixflame;
								p.sendMessage("§c§l[Particle]§r§6 Vous avez achetez la particle §cFLAME.");
							} else if(choix == 2) {
								this.achatem = true;
								this.argent -= prixem;
								p.sendMessage("§c§l[Particle]§r§6 Vous avez achetez la particle �aEMERALD.");
							}
							p.openInventory(inv1);
						}
						break;
					case RED_WOOL:
						if(current.getItemMeta().getDisplayName().equals("§2Ne pas achetez la particle ?")) {
							p.sendMessage("§c§l[Particle]§r§6 Vous avez annulé l'achat.");
							p.openInventory(inv1);
						}
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
		
	}
	
	@EventHandler
	public void createHelix(Player p){
		
		Bukkit.getScheduler().cancelTasks(Main.getInstance());
		
		switch(this.choixhelix) {
			case 1:
				
				Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
			            double radius = 0.5;
			    		Location loc = p.getLocation();
			            for(double t =0; t<=3; t+=0.66){
			            	double x = (float) (radius * Math.cos(t) * Math.random());
			                double z = (float) (radius * Math.sin(t) * Math.random());
			                for(Player player : Bukkit.getOnlinePlayers()){
			                	player.spawnParticle(Particle.FLAME,(float) loc.getX()+x, (float) loc.getY(), (float) loc.getZ()+z, 0,0,0,0,1);
			                } 
			            }
						
					}
					
				}, 0, 1);
				break;
			case 2:
				
				Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
			            double radius = 0.2;
			            Location locc = null;
			    		Location loc = p.getLocation();
			            for(double t =0; t<=1; t+=1){
			            	double x = (float) (radius * Math.cos(t-1.5) * Math.random());
			                double z = (float) (radius * Math.sin(t-1.5) * Math.random());
			                if(loc != locc) {
			                	for(Player player : Bukkit.getOnlinePlayers()) {
			                		player.spawnParticle(Particle.COMPOSTER,(float) loc.getX()+x, (float) loc.getY(), (float) loc.getZ()+z, 0,0,0,0,1);
			                		locc = p.getLocation();
			                	} 
			                }
			            }
						
					}
					
				}, 0, 1);
				break;
			default:
				break;
		}
		
	}
	

}