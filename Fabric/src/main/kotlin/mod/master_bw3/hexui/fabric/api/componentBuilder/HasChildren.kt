package mod.master_bw3.hexui.fabric.api.componentBuilder

interface HasChildren<T: ComponentBuilder<*>> {

    val children: List<ComponentBuilder<*>>

    fun withChildren(children: List<ComponentBuilder<*>>): T

    fun addChild(child: ComponentBuilder<*>): T
}