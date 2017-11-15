package net.simplyrin.serverlistping;

import java.net.InetAddress;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerListPing extends JavaPlugin implements Listener {

	private static ServerListPing plugin;

	@Override
	public void onEnable() {
		plugin = this;
		plugin.getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onPing(ServerListPingEvent event) {
		InetAddress address = event.getAddress();

		plugin.getServer().getConsoleSender().sendMessage("[" + address.getHostAddress() + "] <-> InitialHandler has connected");
	}
}
