package pt.isel.tds

import org.jetbrains.compose.resources.DrawableResource
import slotmac.composeapp.generated.resources.*

private val slotImages = listOf(
    Res.drawable.apple,
    Res.drawable.bar,
    Res.drawable.bell,
    Res.drawable.cherries,
    Res.drawable.diamond,
    Res.drawable.grapes,
    Res.drawable.lemon,
    Res.drawable.seven,
    Res.drawable.watermelon,
    Res.drawable.banana
)

fun Byte.toSlotImageResource(): DrawableResource {
    require(this in slotImages.indices) { "Invalid slot value: $this" }
    return slotImages[this.toInt()]
}