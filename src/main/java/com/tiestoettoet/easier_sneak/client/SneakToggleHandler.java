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
    private static boolean previousCtrlPressed = false;
    private static boolean previousShiftPressed = false;
    // private static long lastToggleTime = 0;
    // private static final long TOGGLE_COOLDOWN = 1; // 500ms cooldown to prevent
    // rapid toggling

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) {
            return;
        }

        // long currentTime = System.currentTimeMillis();

        // Check current key states directly from GLFW
        boolean isCtrlPressed = GLFW.glfwGetKey(mc.getWindow().getWindow(),
                GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS ||
                GLFW.glfwGetKey(mc.getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;

        boolean isShiftPhysicallyPressed = GLFW.glfwGetKey(mc.getWindow().getWindow(),
                GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS ||
                GLFW.glfwGetKey(mc.getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        // Detect Ctrl+Shift combination press (rising edge only)
        if (isCtrlPressed && isShiftPhysicallyPressed &&
                !(previousCtrlPressed && previousShiftPressed)
        // && currentTime - lastToggleTime > TOGGLE_COOLDOWN
        ) {

            // Toggle sneak state
            sneakToggleActive = !sneakToggleActive;
            // lastToggleTime = currentTime;
        }
        // Detect shift-only press when toggle is active (rising edge only)
        else if (sneakToggleActive && isShiftPhysicallyPressed && !isCtrlPressed &&
                !previousShiftPressed
        // && currentTime - lastToggleTime > TOGGLE_COOLDOWN
        ) {

            // Deactivate sneak toggle
            sneakToggleActive = false;
            // lastToggleTime = currentTime;
        }

        // Update previous states
        previousCtrlPressed = isCtrlPressed;
        previousShiftPressed = isShiftPhysicallyPressed;
    }

    /**
     * Called by Mixin to check if we should override normal sneak behavior
     */
    public static boolean shouldOverrideSneak() {
        return sneakToggleActive;
    }

    /**
     * Called by Mixin to get the current sneak state we want
     */
    public static boolean getSneakState() {
        return sneakToggleActive;
    }
}
