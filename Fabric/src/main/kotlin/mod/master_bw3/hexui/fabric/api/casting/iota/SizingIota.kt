package mod.master_bw3.hexui.fabric.api.casting.iota

import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.IotaType
import com.mojang.serialization.Codec
import io.wispforest.owo.ui.core.Sizing
import mod.master_bw3.hexui.fabric.api.casting.SizingData
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilder
import mod.master_bw3.hexui.fabric.api.componentBuilder.ComponentBuilderType
import net.minecraft.client.gui.widget.Widget
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtOps
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text

class SizingIota(payload: SizingData) : Iota(TYPE, payload) {


    override fun isTruthy(): Boolean = true

    override fun toleratesOther(that: Iota?): Boolean =
        that is SizingIota && that.sizing == sizing


    override fun serialize(): NbtElement =
        CODEC.encodeStart(NbtOps.INSTANCE, this).result().orElseThrow()


    val sizing: Sizing
        get() = ((this).payload as SizingData).toSizing()

    companion object TYPE : IotaType<SizingIota>() {
        val CODEC: Codec<SizingIota> = SizingData.CODEC.xmap(::SizingIota) { it.payload as SizingData }

        override fun deserialize(nbt: NbtElement, world: ServerWorld): SizingIota =
            CODEC.parse(NbtOps.INSTANCE, nbt).result().orElseThrow()


        override fun display(tag: NbtElement): Text =
            ComponentBuilderType.getTypeFromNbt(tag as NbtCompound)!!.display(tag)


        override fun color(): Int = 0xFFFFFF

    }
}