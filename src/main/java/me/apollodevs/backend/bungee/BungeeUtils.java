package me.apollodevs.backend.bungee;

import me.apollodevs.backend.Apollo;

import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeUtils {

    public static void connectToServer(final Player player, final String servername) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(servername);
        player.sendPluginMessage(Apollo.getPlugin(), "BungeeCord", out.toByteArray());
    }
    
}
