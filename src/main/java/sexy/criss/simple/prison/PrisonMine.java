package sexy.criss.simple.prison;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PrisonMine {

   public static Map<String, PrisonMine> mines = Maps.newHashMap();
   String id;
   String name;
   boolean needBook;
   int minLevel;
   Location spawn;
   Material icon;


   public PrisonMine(String id, String name, int minLevel, Location spawn, Material icon, boolean needBook) {
      this.id = id;
      this.name = name;
      this.needBook = needBook;
      this.minLevel = minLevel;
      this.spawn = spawn;
      this.icon = icon;
      mines.put(id, this);
   }

   public static Map getRegions() {
      return mines;
   }

   public String getName() {
      return this.name;
   }

   public boolean needBook() {
      return this.needBook;
   }

   public int getMinLevel() {
      return this.minLevel;
   }

   public ItemStack getPrisonIcon(PrisonPlayer pp) {
      ItemStack icon = new ItemStack(this.icon);
      ItemMeta iconMeta = icon.getItemMeta();
      ChatColor c = pp.getLevel() > this.minLevel?ChatColor.GREEN:ChatColor.RED;
      iconMeta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + this.id);
      if(!this.needBook) {
         iconMeta.setLore(Arrays.asList("", c + "Минимальный уровень: " + this.minLevel, ChatColor.DARK_GRAY + "----------", this.name, ChatColor.DARK_GRAY + "----------"));
      } else if(pp.hasCellarAcess()) {
         iconMeta.setLore(Arrays.asList("", ChatColor.GREEN + "Необходимо найти книгу доступа", ChatColor.DARK_GRAY + "----------", this.name, ChatColor.DARK_GRAY + "----------"));
      } else {
         iconMeta.setLore(Arrays.asList("", ChatColor.RED + "Необходимо найти книгу доступа", ChatColor.DARK_GRAY + "----------", this.name, ChatColor.DARK_GRAY + "----------"));
      }

      icon.setItemMeta(iconMeta);
      return icon;
   }

   public static PrisonMine getPrisonMine(String id) {
      return mines.containsKey(id)?(PrisonMine)mines.get(id):null;
   }

   public static boolean isPrisonRegion(String id) {
      return mines.containsKey(id);
   }
}
