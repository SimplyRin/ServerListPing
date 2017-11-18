package net.simplyrin.serverlistping;

import java.net.InetAddress;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerListPing extends JavaPlugin implements Listener {

	private static ServerListPing plugin;

	@Override
	public void onEnable() {
		plugin = this;
		plugin.getServer().getPluginManager().registerEvents(this, this);
		plugin.saveDefaultConfig();
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		plugin.getConfig().set("Address." + player.getAddress().getHostName() + ".UUID", player.getUniqueId().toString());
		plugin.getConfig().set("Address." + player.getAddress().getHostName() + ".Name", player.getName());
		plugin.saveConfig();
	}

	@EventHandler
	public void onPing(ServerListPingEvent event) {
		InetAddress address = event.getAddress();

		if(plugin.getConfig().isSet("Address." + address.getHostName() + ".Name")) {
			plugin.getServer().getConsoleSender().sendMessage("[" + address.getHostName() + "|" + plugin.getConfig().getString("Address." + address.getHostName() + ".Name") + "] <-> InitialHandler has connected");
			return;
		}

		plugin.getServer().getConsoleSender().sendMessage("[" + address.getHostName() + "|Unknown] <-> InitialHandler has connected");
	}
}
