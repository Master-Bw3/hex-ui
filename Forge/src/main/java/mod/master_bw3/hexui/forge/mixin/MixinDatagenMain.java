package mod.master_bw3.hexui.forge.mixin;

import mod.master_bw3.hexui.HexUI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// scuffed workaround for https://github.com/architectury/architectury-loom/issues/189
@Mixin(net.minecraft.data.Main.class)
public class MixinDatagenMain {
    @Inject(method = "main", at = @At("TAIL"), remap = false)
    private static void hexui$systemExitAfterDatagenFinishes(String[] strings, CallbackInfo ci) {
        HexUI.LOGGER.info("Terminating datagen.");
        System.exit(0);
    }
}
