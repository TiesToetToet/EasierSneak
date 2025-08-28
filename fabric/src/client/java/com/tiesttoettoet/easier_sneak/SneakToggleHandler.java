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

        int keyCode = ModKeybinds.toggleSneakKey.getDefaultKey().getCode();

        boolean isKeyPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), keyCode) == GLFW.GLFW_PRESS;

        boolean isSneakPressed = mc.options.sneakKey.isPressed();

        boolean comboPressed = isKeyPressed && isSneakPressed;
        boolean wasComboPressed = previousKeyPressed && previousShiftPressed;

        if (comboPressed && !wasComboPressed) {
            sneakToggleActive = !sneakToggleActive;
        }
        else if (sneakToggleActive && isSneakPressed && !isKeyPressed && !previousShiftPressed) {
            sneakToggleActive = false;
        }

        previousKeyPressed = isKeyPressed;
        previousShiftPressed = isSneakPressed;
    }

    public static boolean getSneakState() {
        return sneakToggleActive;
    }
}
