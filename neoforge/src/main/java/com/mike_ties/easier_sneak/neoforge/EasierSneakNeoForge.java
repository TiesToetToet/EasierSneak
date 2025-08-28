package com.mike_ties.easier_sneak.neoforge;

import net.neoforged.fml.common.Mod;

import com.mike_ties.easier_sneak.EasierSneak;

@Mod(EasierSneak.MOD_ID)
public final class EasierSneakNeoForge {
    public EasierSneakNeoForge() {
        // Run our common setup.
        EasierSneak.init();
    }
}
