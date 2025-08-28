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
        
        boolean isKeyPressed = false;
        
        if (keyCode >= GLFW.GLFW_KEY_A && keyCode <= GLFW.GLFW_KEY_Z) {
            isKeyPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), keyCode) == GLFW.GLFW_PRESS;
        } else if (keyCode == GLFW.GLFW_KEY_LEFT_CONTROL || keyCode == GLFW.GLFW_KEY_RIGHT_CONTROL) {
            isKeyPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS ||
                          GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;
        } else if (keyCode == GLFW.GLFW_KEY_LEFT_ALT || keyCode == GLFW.GLFW_KEY_RIGHT_ALT) {
            isKeyPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_ALT) == GLFW.GLFW_PRESS ||
                          GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_ALT) == GLFW.GLFW_PRESS;
        } else {
            isKeyPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), keyCode) == GLFW.GLFW_PRESS;
        }

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
