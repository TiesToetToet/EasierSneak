package com.tiestoettoet.easier_sneak.client;

import com.tiestoettoet.easier_sneak.EasierSneak;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = EasierSneak.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class SneakToggleHandler {

    private static boolean sneakToggleActive = false;
    private static boolean previousKeyPressed = false;
    private static boolean previousShiftPressed = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) {
            return;
        }

        boolean isKeyPressed = ModKeybinds.toggleSneakKey.isDown();

        boolean isShiftPressed = mc.options.keyShift.isDown();

        boolean comboPressed = isKeyPressed && isShiftPressed;
        boolean wasComboPressed = previousKeyPressed && previousShiftPressed;

        if (comboPressed && !wasComboPressed) {
            sneakToggleActive = !sneakToggleActive;
        } else if (sneakToggleActive && isShiftPressed && !isKeyPressed && !previousShiftPressed) {
            sneakToggleActive = false;
        }

        previousKeyPressed = isKeyPressed;
        previousShiftPressed = isShiftPressed;
    }

    public static boolean getSneakState() {
        return sneakToggleActive;
    }
}
