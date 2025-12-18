package pt.isel.tds

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Slot Machine",
        state = WindowState(size = DpSize(400.dp, 300.dp)))
    { MaterialTheme { SlotMachineApp() } }
}

@Composable
fun SlotMachineApp() {
    val slotMachineViewModel = remember { SlotMachineViewModel() }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//TODO: II.3
/*  Implemente o código em falta da função SlotMachineApp para compor a aplicação utilizando as funções
    composable indicadas no trecho de código.  */
    }
}

@Composable
fun PlayerInputDetails(nameGetter: () -> String, nameSetter: (String) -> Unit) {
//TODO: II.4
/*  Implemente as funções PlayerInputDetails, SlotMachine, ResultPanel de acordo com o exemplo das
    janelas apresentadas e com os protótipos apresentados no trecho de código. Assuma a existência da função
    extensão de Byte.toSlotImageFilename(), que retorna o nome do recurso (imagem) correspondente ao valor
    do byte. */
}

@Composable
fun SlotMachine(slotState: () -> SlotState, isEnabled: () -> Boolean, play: () -> Unit) {
//TODO: II.4
}

@Composable
fun ResultPanel(slotMachineViewModel: SlotMachineViewModel) {
//TODO: II.4
}