package com.tiestoettoet.easier_sneak.client;

import com.tiestoettoet.easier_sneak.EasierSneak;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = EasierSneak.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModKeybinds {
    public static final String KEY_CATEGORY_EASIER_SNEAK = "key.categories.easier_sneak";
    public static final String KEY_TOGGLE_SNEAK = "key.easier_sneak.toggle_sneak";

    public static KeyMapping toggleSneakKey;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        toggleSneakKey = new KeyMapping(
                KEY_TOGGLE_SNEAK,
                GLFW.GLFW_KEY_LEFT_CONTROL,
                KEY_CATEGORY_EASIER_SNEAK);

        event.register(toggleSneakKey);
    }
}
