package com.tiesttoettoet.easier_sneak;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class SneakToggleHandler {

    private static boolean sneakToggleActive = false;
    private static boolean previousKeyPressed = false;
    private static boolean previousShiftPressed = false;

    public static void onClientTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.currentScreen != null) {
            return;
        }

        boolean isKeyPressed = ModKeybinds.toggleSneakKey.isPressed();

        boolean isShiftPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(),
                GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS ||
                GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        boolean comboPressed = isKeyPressed && isShiftPressed;
        boolean wasComboPressed = previousKeyPressed && previousShiftPressed;

        if (comboPressed && !wasComboPressed) {
            sneakToggleActive = !sneakToggleActive;
        }
        else if (sneakToggleActive && isShiftPressed && !isKeyPressed && !previousShiftPressed) {
            sneakToggleActive = false;
        }

        previousKeyPressed = isKeyPressed;
        previousShiftPressed = isShiftPressed;
    }

    public static boolean getSneakState() {
        return sneakToggleActive;
    }
}
