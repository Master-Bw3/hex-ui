package mod.master_bw3.hexui.fabric.registry

import at.petrak.hexcasting.api.casting.eval.vm.ContinuationFrame
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexContinuationTypes
import mod.master_bw3.hexui.registry.HexUIRegistrar

class FabricHexUIContinuationTypes :
    HexUIRegistrar<ContinuationFrame.Type<*>>(HexRegistries.CONTINUATION_TYPE, { HexContinuationTypes.REGISTRY }) {


}