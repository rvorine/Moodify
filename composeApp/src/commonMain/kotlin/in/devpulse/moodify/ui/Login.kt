package `in`.devpulse.moodify.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.devpulse.moodify.AppUiEvent
import `in`.devpulse.moodify.repository.getSpotifyClient
import `in`.devpulse.moodify.res.colorAccent
import moodify.composeapp.generated.resources.Res
import moodify.composeapp.generated.resources.bg_login
import moodify.composeapp.generated.resources.ic_waveform
import org.jetbrains.compose.resources.painterResource

@Composable
fun Login(
    onEvent: ((AppUiEvent) -> Unit)? = null,
    onLoginSuccess: ((token: String) -> Unit)? = null
) {
    Scaffold(
        modifier = Modifier.background(Color.Black).windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().paint(
                painter = painterResource(Res.drawable.bg_login),
                contentScale = ContentScale.Crop
            ).padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .padding(4.dp)
                            .background(colorAccent, CircleShape)
                            .clip(CircleShape),
                        painter = painterResource(Res.drawable.ic_waveform),
                        contentDescription = ""
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = colorAccent)) {
                                append("M")
                            }
                            append("oodify")
                        },
                        modifier = Modifier.padding(start = 10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = Color.White
                        )
                    )
                }

                Spacer(Modifier.height(5.dp))

                Text(buildAnnotatedString {
                    append("The ")
                    withStyle(style = SpanStyle(color = colorAccent)) {
                        append("Mood\n")
                    }
                    append("of life with \nthe ")
                    withStyle(style = SpanStyle(color = colorAccent)) {
                        append("Music\n")
                    }
                    append("in your \nlife.")
                }, style = TextStyle(fontSize = 65.sp, color = Color.White))
            }

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = colorAccent),
                onClick = {
                    getSpotifyClient().authenticateSpotify { token ->
                        onLoginSuccess?.invoke(token ?: "")
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Let's get started")
            }
        }
    }
}