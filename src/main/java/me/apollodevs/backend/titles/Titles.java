package me.apollodevs.backend.titles;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Titles {
	
    @Deprecated
    public static void sendTitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, final String message) {
        sendTitle(player, fadeIn, stay, fadeOut, message, null);
    }
    
    @Deprecated
    public static void sendSubtitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, final String message) {
        sendTitle(player, fadeIn, stay, fadeOut, null, message);
    }
    
    @Deprecated
    public static void sendFullTitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, final String title, final String subtitle) {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }
    
    @Deprecated
    public static Integer getPlayerProtocol(final Player player) {
        return 47;
    }
    
    public static void sendPacket(final Player player, final Object packet) {
        try {
            final Object handle = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(player, new Object[0]);
            final Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Class<?> getNMSClass(final String name) {
        final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * @param player
     * @param fadeIn
     * @param stay
     * @param fadeOut
     * @param title
     * @param subtitle
     */
    public static void sendTitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, final String title, final String subtitle) {
        try {
            if (title != null) {
                final Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
                final Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
                final Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
                final Object titlePacket = titleConstructor.newInstance(enumTitle, chatTitle, fadeIn, stay, fadeOut);
                sendPacket(player, titlePacket);
            }
            if (subtitle != null) {
                final Object enumSubtitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
                final Object chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");
                final Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
                final Object subtitlePacket = subtitleConstructor.newInstance(enumSubtitle, chatSubtitle, fadeIn, stay, fadeOut);
                sendPacket(player, subtitlePacket);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendTabTitle(final Player player, String header, String footer) {
        if (header == null) {
            header = "";
        }
        header = ChatColor.translateAlternateColorCodes('&', header);
        if (footer == null) {
            footer = "";
        }
        footer = ChatColor.translateAlternateColorCodes('&', footer);
        header = header.replaceAll("%player%", player.getDisplayName());
        footer = footer.replaceAll("%player%", player.getDisplayName());
        try {
            final Object tabHeader = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
            final Object tabFooter = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");
            final Constructor<?> titleConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNMSClass("IChatBaseComponent"));
            final Object packet = titleConstructor.newInstance(tabHeader);
            final Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, tabFooter);
            sendPacket(player, packet);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sendActionBar(final Player player, final String message) {
        String nmsver = Bukkit.getServer().getClass().getPackage().getName();
        nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
        try {
            final Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
            final Object p = c1.cast(player);
            Object ppoc = null;
            final Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
            final Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".Packet");
            if (nmsver.equalsIgnoreCase("v1_8_R1") || !nmsver.startsWith("v1_8_")) {
                final Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".ChatSerializer");
                final Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                final Method m3 = c4.getDeclaredMethod("a", String.class);
                final Object cbc = c5.cast(m3.invoke(c4, "{\"text\": \"" + message + "\"}"));
                ppoc = c2.getConstructor(c5, Byte.TYPE).newInstance(cbc, (byte)2);
            }
            else {
                final Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
                final Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                final Object o = c4.getConstructor(String.class).newInstance(message);
                ppoc = c2.getConstructor(c5, Byte.TYPE).newInstance(o, (byte)2);
            }
            final Method m2 = c1.getDeclaredMethod("getHandle", (Class<?>[])new Class[0]);
            final Object h = m2.invoke(p, new Object[0]);
            final Field f1 = h.getClass().getDeclaredField("playerConnection");
            final Object pc = f1.get(h);
            final Method m3 = pc.getClass().getDeclaredMethod("sendPacket", c3);
            m3.invoke(pc, ppoc);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
