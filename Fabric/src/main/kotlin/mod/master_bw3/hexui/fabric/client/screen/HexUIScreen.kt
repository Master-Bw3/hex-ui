package mod.master_bw3.hexui.fabric.client.screen

import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.iota.ListIota
import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import mod.master_bw3.hexui.fabric.api.componentBuilder.ButtonComponentBuilder
import net.minecraft.text.Text


class HexUIScreen : BaseOwoScreen<FlowLayout>() {
    override fun shouldPause(): Boolean = false

    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    var model = initModel()
    var msg = Msg.NONE

    override fun build(rootComponent: FlowLayout) {
        println("build")
        this.uiAdapter.rootComponent.child(view(model))
    }


    override fun tick() {
        val newModel = update(this.msg, this.model)
        this.msg = Msg.NONE

        if (newModel != this.model) {
            this.model = newModel;
            this.uiAdapter.rootComponent.clearChildren()
            this.uiAdapter.rootComponent.child(view(newModel))
        }

        super.tick()
    }

    data class Model(val value: Int)

    enum class Msg {
        NONE,
        INCREMENT,
        DECREMENT,
    }

    fun initModel(): Model {
        return Model(value = 0)
    }

    fun update(msg: Msg, model: Model): Model {
        return when (msg) {
            Msg.NONE -> model
            Msg.INCREMENT -> model.copy(value = model.value + 1)
            Msg.DECREMENT -> model.copy(value = model.value - 1)
        }
    }

    fun view(model: Model): FlowLayout {

        val inc = Components.button(Text.literal("/\\")) { button -> this.msg = Msg.INCREMENT }
        val dec = Components.button(Text.literal("\\/")) { button -> this.msg = Msg.DECREMENT }

        val test = ButtonComponentBuilder(Text.literal("test"), IotaType.serialize(ListIota(listOf())), listOf()).build()


        val counter = Components.label(Text.literal(model.value.toString()))


        val root = Containers.verticalFlow(Sizing.fill(100), Sizing.fill(100))
        root.child(inc)
        root.child(counter)
        root.child(dec)
        root.child(test)


        return root
    }

}