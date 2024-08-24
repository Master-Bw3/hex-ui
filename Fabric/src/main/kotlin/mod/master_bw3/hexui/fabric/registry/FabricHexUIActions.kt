package mod.master_bw3.hexui.fabric.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexActions
import mod.master_bw3.hexui.fabric.casting.actions.*
import mod.master_bw3.hexui.fabric.componentBuilder.container.LayoutComponentBuilder
import mod.master_bw3.hexui.registry.HexUIRegistrar

object FabricHexUIActions : HexUIRegistrar<ActionRegistryEntry>(HexRegistries.ACTION, { HexActions.REGISTRY }) {

    @JvmField
    val MAKE_BUTTON = make("component.make_button", HexDir.EAST, "wdewd", OpMakeButton)

    @JvmField
    val MAKE_LAYOUT_VERTICAL = make("component.make_layout_vertical", HexDir.NORTH_EAST, "eewdwwdew", OpMakeLayout(
        LayoutComponentBuilder.Algorithm.VERTICAL))

    @JvmField
    val MAKE_LAYOUT_HORIZONTAL = make("component.make_layout_horizontal", HexDir.EAST, "eeewdqdee", OpMakeLayout(
        LayoutComponentBuilder.Algorithm.HORIZONTAL))

    @JvmField
    val SET_CHILDREN = make("component.set_children", HexDir.EAST, "deedadd", OpSetChildren)

    @JvmField
    val ADD_CHILD = make("component.add_child", HexDir.WEST, "aqqadaa", OpAddChild)

    @JvmField
    val SET_GAP = make("component.set_gap", HexDir.EAST, "eeedqqaeedqd", OpSetGap)

    @JvmField
    val DISPLAY_SCREEN = make("display_screen", HexDir.EAST, "eeeeeweee", OpDisplayScreen)

    private fun make(name: String, startDir: HexDir, signature: String, action: Action) =
        make(name, startDir, signature) { action }

    private fun make(name: String, startDir: HexDir, signature: String, getAction: () -> Action) = register(name) {
        ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), getAction())
    }
}