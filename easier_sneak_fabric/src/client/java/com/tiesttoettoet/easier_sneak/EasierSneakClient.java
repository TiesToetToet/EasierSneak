package com.tiesttoettoet.easier_sneak;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class EasierSneakClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EasierSneak.LOGGER.info("EasierSneak client is starting up!");
        
        // Register the client tick handler
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            SneakToggleHandler.onClientTick();
        });
    }
}
