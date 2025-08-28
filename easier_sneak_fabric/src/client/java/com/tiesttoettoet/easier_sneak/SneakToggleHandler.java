package com.tiesttoettoet.easier_sneak;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class SneakToggleHandler {

    private static boolean sneakToggleActive = false;
    private static boolean previousCtrlPressed = false;
    private static boolean previousShiftPressed = false;
    private static int debugCounter = 0;

    public static void onClientTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.currentScreen != null) {
            return;
        }

        // Debug: Print every 100 ticks to see if handler is working
        if (debugCounter++ % 100 == 0) {
            EasierSneak.LOGGER.info("SneakToggleHandler tick - sneakToggleActive: {}", sneakToggleActive);
        }

        // Check current key states directly from GLFW
        boolean isCtrlPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(),
                GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS ||
                GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;

        boolean isShiftPhysicallyPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(),
                GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS ||
                GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        // Detect Ctrl+Shift combination press (rising edge only)
        if (isCtrlPressed && isShiftPhysicallyPressed &&
                !(previousCtrlPressed && previousShiftPressed)) {

            // Toggle sneak state
            sneakToggleActive = !sneakToggleActive;
            EasierSneak.LOGGER.info("Ctrl+Shift detected! Toggle state: {}", sneakToggleActive);
        }
        // Detect shift-only press when toggle is active (rising edge only)
        else if (sneakToggleActive && isShiftPhysicallyPressed && !isCtrlPressed &&
                !previousShiftPressed) {

            // Deactivate sneak toggle
            sneakToggleActive = false;
            EasierSneak.LOGGER.info("Shift-only detected! Disabling sneak toggle");
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
