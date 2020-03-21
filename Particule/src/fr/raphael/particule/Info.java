package fr.raphael.particule;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Info {
	
	boolean achatflame;
	boolean achatem = false;
	double argent = 800;
	
	public ItemStack getItem(Material mat, String name, boolean enchant) {
		ItemStack it = new ItemStack(mat, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(name);
		if(enchant == true) {
			itM.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
			itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		it.setItemMeta(itM);
		return it;
	}
	
	public Inventory inv(int numero) {
		switch(numero) {
			case 1:
				Inventory inv2 = Bukkit.createInventory(null, 27, "§cMarché");
				inv2.setItem(11, getItem(Material.FLINT_AND_STEEL, "§cFLAME", achatflame));
				inv2.setItem(15, getItem(Material.EMERALD, "§aEMERALD", achatem));
				inv2.setItem(0, getItem(Material.BOOK, "§eMenu", false));
				inv2.setItem(4, getItem(Material.GOLD_INGOT, "§eVotre argent est de §c" + argent + ".", false));
				return inv2;
			case 2:
				Inventory inv3 = Bukkit.createInventory(null, 27, "§2Inventaire");
				if(achatflame == true) {
					inv3.setItem(11, getItem(Material.FLINT_AND_STEEL, "§cFLAME", false));
				} else if (achatem == true) {
					inv3.setItem(15, getItem(Material.EMERALD, "§aEMERALD", false));
				}
				inv3.setItem(0, getItem(Material.BOOK, "§eMenu", false));
				return inv3;
			case 3:
				Inventory invconfirm = Bukkit.createInventory(null, 27, "§8Confirmation");
				invconfirm.setItem(11, getItem(Material.GREEN_WOOL, "§2Achetez la particle ?", false));
				invconfirm.setItem(15, getItem(Material.RED_WOOL, "§2Ne pas achetez la particle ?", false));
				return invconfirm;
			case 4:
				Inventory invmenu = Bukkit.createInventory(null, 27, "§eMenu");
				invmenu.setItem(11, getItem(Material.DIAMOND_BLOCK, "§cMarché", false));
				invmenu.setItem(15, getItem(Material.CHEST, "§2Inventaire", false));
				return invmenu;
			default:
				break;
		}
		return null;
	}

}
