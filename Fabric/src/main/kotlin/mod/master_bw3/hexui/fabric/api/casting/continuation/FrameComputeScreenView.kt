package mod.master_bw3.hexui.fabric.api.casting.continuation

import at.petrak.hexcasting.api.casting.SpellList
import at.petrak.hexcasting.api.casting.eval.CastResult
import at.petrak.hexcasting.api.casting.eval.ResolvedPatternType
import at.petrak.hexcasting.api.casting.eval.vm.CastingVM
import at.petrak.hexcasting.api.casting.eval.vm.ContinuationFrame
import at.petrak.hexcasting.api.casting.eval.vm.FrameEvaluate
import at.petrak.hexcasting.api.casting.eval.vm.SpellContinuation
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.casting.mishaps.MishapNotEnoughArgs
import at.petrak.hexcasting.api.utils.hasList
import at.petrak.hexcasting.api.utils.putBoolean
import at.petrak.hexcasting.api.utils.putCompound
import at.petrak.hexcasting.api.utils.serializeToNBT
import at.petrak.hexcasting.common.lib.hex.HexEvalSounds
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import com.mojang.serialization.codecs.RecordCodecBuilder
import mod.master_bw3.hexui.fabric.api.casting.getComponent
import mod.master_bw3.hexui.fabric.screen.HexScreenHandler
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld

data class FrameComputeScreenView(
    val baseStack: List<Iota>?,
    val model: Iota, val view: SpellList
) : ContinuationFrame {
    override val type: ContinuationFrame.Type<*>
        get() = TYPE

    override fun breakDownwards(stack: List<Iota>): Pair<Boolean, List<Iota>> =
        false to stack

    override fun evaluate(continuation: SpellContinuation, level: ServerWorld, harness: CastingVM): CastResult {
        return if (baseStack == null) {
            //init

            val newContinuation = continuation
                .pushFrame(this.copy(baseStack = harness.image.stack))
                .pushFrame(FrameEvaluate(view, true))

            CastResult(
                model,
                newContinuation,
                harness.image.copy(stack = listOf(model)),
                listOf(),
                ResolvedPatternType.EVALUATED,
                HexEvalSounds.NOTHING
            )
        } else {
            val result = harness.image.stack
            if (result.isEmpty()) throw MishapNotEnoughArgs(1, 0)

            ((harness.env.castingEntity as? ServerPlayerEntity)
                ?.currentScreenHandler as? HexScreenHandler)
                ?.view
                ?.set(
                    result.takeLast(1).getComponent(0, 1)
                )

            CastResult(
                ListIota(view),
                continuation,
                harness.image.copy(stack = baseStack),
                listOf(),
                ResolvedPatternType.EVALUATED,
                HexEvalSounds.HERMES
            )
        }
    }

    override fun serializeToNBT(): NbtCompound {
        val nbt = NbtCompound()

        nbt.put("base_stack", baseStack?.serializeToNBT())
        nbt.putCompound("model", IotaType.serialize(model))
        nbt.put("view", view.serializeToNBT())

        return nbt
    }

    override fun size(): Int {
        return 2 + view.size()
    }

    companion object TYPE : ContinuationFrame.Type<FrameComputeScreenView> {

        override fun deserializeFromNBT(tag: NbtCompound, world: ServerWorld): FrameComputeScreenView {
            val baseStack = if (tag.hasList("base_stack", NbtElement.COMPOUND_TYPE.toInt())) {
                HexIotaTypes.LIST.deserialize(
                    tag.getList("base_stack", NbtElement.COMPOUND_TYPE.toInt()),
                    world
                )!!.list.toList()
            } else null

            val model = IotaType.deserialize(tag.getCompound("model"), world)

            val view =
                HexIotaTypes.LIST.deserialize(tag.getList("view", NbtElement.COMPOUND_TYPE.toInt()), world)!!.list

            return FrameComputeScreenView(baseStack, model, view)
        }
    }
}