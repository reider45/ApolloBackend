package me.apollodevs.backend.nametags;


import me.apollodevs.backend.Apollo;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class NameTask {

	BukkitTask task;
	
    public NameTask() {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(Apollo.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Nametags.getAPI().refresh();
            }
        }, 0, 100);
    }
    
    public void stop() {
    	task.cancel();
    }

}