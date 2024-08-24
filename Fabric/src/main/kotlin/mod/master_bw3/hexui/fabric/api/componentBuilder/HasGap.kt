package mod.master_bw3.hexui.fabric.api.componentBuilder

interface HasGap<T: ComponentBuilder<*>> {
    val gap: Int

    fun withGap(gap: Int): T
}