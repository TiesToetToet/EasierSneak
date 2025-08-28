package com.tiesttoettoet.easier_sneak;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static final String KEY_CATEGORY_EASIER_SNEAK = "key.categories.easier_sneak";
    public static final String KEY_TOGGLE_SNEAK = "key.easier_sneak.toggle_sneak";

    public static KeyBinding toggleSneakKey;

    public static void registerKeyBindings() {
        toggleSneakKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TOGGLE_SNEAK,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_CONTROL,
                KEY_CATEGORY_EASIER_SNEAK));
    }
}
