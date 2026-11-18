
package com.prt.racingdashboard

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private val fundo = Color.rgb(12, 12, 15)
    private val vermelho = Color.rgb(230, 25, 35)
    private val branco = Color.WHITE
    private val cinzento = Color.rgb(150, 150, 150)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val principal = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setBackgroundColor(fundo)
            setPadding(24, 16, 24, 16)
        }

        val velocidade = criarColuna()
        velocidade.addView(texto("VELOCIDADE", 20f, cinzento))
        velocidade.addView(texto("0", 100f, branco))
        velocidade.addView(texto("KM/H", 22f, vermelho))

        val direita = criarColuna()

        direita.addView(texto("RPM", 22f, cinzento))
        direita.addView(texto("0", 65f, branco))

        val luzes = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }

        for (i in 1..8) {
            val luz = View(this).apply {
                setBackgroundColor(
                    if (i <= 3) vermelho else Color.DKGRAY
                )
            }

            val params = LinearLayout.LayoutParams(18, 18)
            params.setMargins(5, 12, 5, 12)
            luzes.addView(luz, params)
        }

        direita.addView(luzes)
        direita.addView(texto("PRT PORTUGAL RACING TEAM", 17f, branco))
        direita.addView(texto("AGUARDAR TELEMETRIA", 14f, cinzento))

        principal.addView(
            velocidade,
            LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1f)
        )

        principal.addView(
            direita,
            LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT, 1f)
        )

        setContentView(principal)
    }

    private fun criarColuna(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
    }

    private fun texto(
        valor: String,
        tamanho: Float,
        cor: Int
    ): TextView {
        return TextView(this).apply {
            text = valor
            textSize = tamanho
            setTextColor(cor)
            gravity = Gravity.CENTER
            setPadding(4, 4, 4, 4)
        }
    }
}
