package com.tiesttoettoet.easier_sneak.mixin.client;

import com.tiesttoettoet.easier_sneak.SneakToggleHandler;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTickEnd(boolean slowDown, float f, CallbackInfo ci) {
        KeyboardInput self = (KeyboardInput) (Object) this;

        // Override the sneak input if our toggle handler is active
        if (SneakToggleHandler.getSneakState()) {
            self.sneaking = SneakToggleHandler.getSneakState();
        }
    }
}
