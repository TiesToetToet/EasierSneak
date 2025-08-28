package com.tiesttoettoet.easier_sneak;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class EasierSneakClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeybinds.registerKeyBindings();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            SneakToggleHandler.onClientTick();
        });
    }
}
