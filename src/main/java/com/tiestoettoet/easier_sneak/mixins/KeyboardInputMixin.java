package com.tiestoettoet.easier_sneak.mixins;

import com.tiestoettoet.easier_sneak.client.SneakToggleHandler;
import net.minecraft.client.player.KeyboardInput;
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
        if (SneakToggleHandler.shouldOverrideSneak()) {
            self.shiftKeyDown = SneakToggleHandler.getSneakState();
        }
    }
}
