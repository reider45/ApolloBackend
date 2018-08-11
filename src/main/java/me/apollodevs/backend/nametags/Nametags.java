package me.apollodevs.backend.nametags;

/**
 * Class used to get the API Methods
 */
public class Nametags {

    private static NametagAPI api;
    private static NameTask task;
    
    public Nametags() {
    	api = new NametagAPI();
    	task = new NameTask();
    }
    
    public static NametagAPI getAPI() {
    	return api;
    }
    
    public static void stop() {
    	task.stop();
    }
    
    

}
