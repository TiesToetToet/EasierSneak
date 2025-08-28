package com.mike_ties.easier_sneak.neoforge.client;

import com.mike_ties.easier_sneak.EasierSneak;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.MinecraftClient;

@EventBusSubscriber(modid = EasierSneak.MOD_ID, value = Dist.CLIENT)
public class SneakToggleHandler {
    private static boolean sneakToggleActive = false;
    private static boolean previousCtrlPressed = false;
    private static boolean previousShiftPressed = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.currentScreen != null) {
            return;
        }

        boolean isCtrlPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS ||
                                GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;

        boolean isShiftPressed = GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS ||
                                GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        if (
            isCtrlPressed &&
            isShiftPressed &&
            !previousCtrlPressed &&
            !previousShiftPressed
        ) {
            sneakToggleActive = !sneakToggleActive;
        } else if (
            sneakToggleActive &&
            isShiftPressed &&
            !isCtrlPressed &&
            !previousShiftPressed
        ) {
            sneakToggleActive = false;
        }

        previousCtrlPressed = isCtrlPressed;
        previousShiftPressed = isShiftPressed;
    }

    public static boolean isSneakToggleActive() {
        return sneakToggleActive;
    }
}