package mod.master_bw3.hexui.fabric.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.text.Text


class HexUIScreen : BaseOwoScreen<FlowLayout>() {
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

        if (newModel != model) {
            this.uiAdapter.rootComponent.clearChildren()
            this.uiAdapter.rootComponent.child(view(newModel))
        }

        super.tick()
    }

    data class Model(val show: Boolean)

    enum class Msg {
        NONE,
        HIDE
    }

    fun initModel(): Model {
        return Model(show = true)
    }

    fun update(msg: Msg, model: Model): Model {
        return when (msg) {
            Msg.NONE -> model
            Msg.HIDE -> Model(show = false)
        }
    }

    fun view(model: Model): FlowLayout {

        val button = Components.button(Text.literal("click me")) { button -> this.msg = Msg.HIDE }

        val root = Containers.verticalFlow(Sizing.fill(100), Sizing.fill(100))

        if(model.show)  root.child(button)

        return root
    }

}