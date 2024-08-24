package mod.master_bw3.hexui.fabric.api.casting

import com.mojang.datafixers.util.Either
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import mod.master_bw3.hexui.HexUI
import net.minecraft.util.Identifier
import com.mojang.datafixers.util.Pair
import io.wispforest.owo.ui.core.Sizing
import mod.master_bw3.hexui.fabric.api.casting.SizingData.Fixed.Companion

sealed interface SizingData {
    fun toSizing(): Sizing

    fun getCodec(): Codec<out SizingData>

    data class Fixed(val value: Int) : SizingData {
        override fun toSizing(): Sizing = Sizing.fixed(value)

        override fun getCodec(): Codec<out SizingData> = CODEC

        companion object {
            val CODEC: Codec<Fixed> =
                Codec.pair(Identifier.CODEC.fieldOf("type").codec(), Codec.INT.fieldOf("value").codec())
                    .comapFlatMap({
                        if (it.first == HexUI.id("fixed")) {
                            DataResult.success(Fixed(it.second))
                        } else DataResult.error { "wrong type" }
                    }, { Pair(HexUI.id("fixed"), it.value) })
        }
    }

    data class Content(val padding: Int) : SizingData {
        override fun toSizing(): Sizing = Sizing.content(padding)

        override fun getCodec(): Codec<out SizingData> = CODEC

        companion object {
            val CODEC: Codec<Content> =
                Codec.pair(Identifier.CODEC.fieldOf("type").codec(), Codec.INT.fieldOf("padding").codec())
                    .comapFlatMap({
                        if (it.first == HexUI.id("content")) {
                            DataResult.success(Content(it.second))
                        } else DataResult.error { "wrong type" }
                    }, { Pair(HexUI.id("content"), it.padding) })
        }
    }

    data class Fill(val percent: Int) : SizingData {
        override fun toSizing(): Sizing = Sizing.fill(percent)

        override fun getCodec(): Codec<out SizingData> = CODEC

        companion object {
            val CODEC: Codec<Fill> =
                Codec.pair(Identifier.CODEC.fieldOf("type").codec(), Codec.INT.fieldOf("padding").codec())
                    .comapFlatMap({ x ->
                        if (x.first == HexUI.id("fill")) {
                            DataResult.success(Fill(x.second))
                        } else DataResult.error { "wrong type" }
                    }, { Pair(HexUI.id("fill"), it.percent) })
        }
    }

    companion object {
        val CODEC: Codec<SizingData> =
            Codec.either(
                Fixed.CODEC,
                Codec.either(Fill.CODEC, Content.CODEC)
                    .flatComapMap(SizingData::eitherOut, SizingData::eitherIn)
            ).flatComapMap(SizingData::eitherOut, SizingData::eitherIn)

        private fun <A : SizingData, B : SizingData> eitherOut(either: Either<A, B>): SizingData {
            return either.left().map { it as SizingData }.orElseGet { either.right().orElseThrow() }
        }

        private inline fun <reified A : SizingData, reified B : SizingData> eitherIn(data: SizingData): DataResult<Either<A, B>> =
            when (data) {
                is A -> DataResult.success(Either.left(data))
                is B -> DataResult.success(Either.right(data))
                else -> DataResult.error { "wrong type" }
            }

    }
}

