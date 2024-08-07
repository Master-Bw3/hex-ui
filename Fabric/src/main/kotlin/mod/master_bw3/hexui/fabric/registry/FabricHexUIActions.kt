package mod.master_bw3.hexui.fabric.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexActions
import mod.master_bw3.hexui.fabric.casting.actions.OpDisplayScreen
import mod.master_bw3.hexui.fabric.casting.actions.OpMakeButton
import mod.master_bw3.hexui.registry.HexUIRegistrar

object FabricHexUIActions : HexUIRegistrar<ActionRegistryEntry>(HexRegistries.ACTION, { HexActions.REGISTRY }) {

    @JvmField
    val MAKE_BUTTON = make("make_button", HexDir.NORTH_EAST, "wdewd", OpMakeButton)

    @JvmField
    val DISPLAY_SCREEN = make("display_screen", HexDir.NORTH_EAST, "eeeeeweee", OpDisplayScreen)

    private fun make(name: String, startDir: HexDir, signature: String, action: Action) =
        make(name, startDir, signature) { action }

    private fun make(name: String, startDir: HexDir, signature: String, getAction: () -> Action) = register(name) {
        ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), getAction())
    }
}