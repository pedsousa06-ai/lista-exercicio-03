package com.example.lista03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Azul = Color(0xFF4558B8)
private val Fundo = Color(0xFFFAF8FD)
private val TextoEscuro = Color(0xFF2B2B33)

private const val IDADE_MINIMA = 0
private const val IDADE_MAXIMA = 180

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TelaIdade()
            }
        }
    }
}

@Composable
fun TelaIdade() {
    var idade by remember { mutableIntStateOf(17) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fundo)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Qual é a sua idade?",
            color = Azul,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Aperte os botões para informar a sua idade",
            color = TextoEscuro,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = idade.toString(),
            color = TextoEscuro,
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // O botão é desabilitado no limite, então não dá para passar de 0 nem de 180
            BotaoIdade(
                texto = "-",
                habilitado = idade > IDADE_MINIMA,
                aoClicar = { if (idade > IDADE_MINIMA) idade-- }
            )
            BotaoIdade(
                texto = "+",
                habilitado = idade < IDADE_MAXIMA,
                aoClicar = { if (idade < IDADE_MAXIMA) idade++ }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = if (idade >= 18) "Você é MAIOR de idade" else "Você é MENOR de idade",
            color = Azul,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BotaoIdade(texto: String, habilitado: Boolean, aoClicar: () -> Unit) {
    Button(
        onClick = aoClicar,
        enabled = habilitado,
        modifier = Modifier.size(64.dp),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Azul)
    ) {
        Text(text = texto, fontSize = 32.sp, color = Color.White)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
fun TelaIdadePreview() {
    MaterialTheme {
        TelaIdade()
    }
}