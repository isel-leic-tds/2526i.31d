package pt.isel.tds

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Slot Machine",
        state = WindowState(size = DpSize(400.dp, 400.dp)))
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
        PlayerInputDetails(
            { slotMachineViewModel.playerName },
            { slotMachineViewModel.playerName = it }
        )
        SlotMachine(
            {slotMachineViewModel.slotState},
            slotMachineViewModel::isPlayerNameValid,
            slotMachineViewModel::play
        )
        ResultPanel(slotMachineViewModel)
    }
}

@Composable
fun PlayerInputDetails(nameGetter: () -> String, nameSetter: (String) -> Unit) {
//TODO: II.4
/*  Implemente as funções PlayerInputDetails, SlotMachine, ResultPanel de acordo com o exemplo das
    janelas apresentadas e com os protótipos apresentados no trecho de código. Assuma a existência da função
    extensão de Byte.toSlotImageFilename(), que retorna o nome do recurso (imagem) correspondente ao valor
    do byte. */
    OutlinedTextField(nameGetter(), onValueChange = nameSetter, singleLine = true)
}

@Composable
fun SlotMachine(slotState: () -> SlotState, isEnabled: () -> Boolean, play: () -> Unit) {
//TODO: II.4
    Row {
        slotState().slots.forEach {
            Image( painterResource(it.toSlotImageResource()), null,
                Modifier.padding(10.dp).size(100.dp,80.dp))
        }
    }
    Button(onClick = play, enabled = isEnabled()) { Text("Play") }
}

@Composable
fun ResultPanel(vm: SlotMachineViewModel) {
//TODO: II.4
    val text = when {
        !vm.isPlayerNameValid() -> "Insert your name and press play!"
        !vm.isWinner() -> "Better luck next time ${vm.playerName}"
        else -> "Congratulations ${vm.playerName}"
    }
    Text(text, modifier = Modifier.padding(10.dp))
}