// código original

//package com.example.aula7exercicio2
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.TextView
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.google.android.material.snackbar.Snackbar
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // cria um objeto representando o layout da tela
//        var meuLayout = findViewById<ConstraintLayout>(R.id.meuLayout)
//        // define um evento ao clicar no layout (qualquer parte da tela)
//        meuLayout.setOnClickListener { view ->
//            // cria um objeto que representa o TextView da tela
//            var rotulo = findViewById<TextView>(R.id.textView)
//            // mostra um Snackbar curto
////            Snackbar.make(view, "Mensagem", Snackbar.LENGTH_SHORT).show()
//            // mostra um Snackbar longo
////            Snackbar.make(view, "Mensagem", Snackbar.LENGTH_LONG).show()
//            // mostra um Snackbar de fechar
//            var s = Snackbar.make(view, rotulo.text, Snackbar.LENGTH_INDEFINITE)
//            s.setAction("Fechar", { s.dismiss() }) // define a ação de fechar
//            s.show()
//        }
//    }
//}


// código cam as melhorias
package com.example.aula7exercicio2

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constraintLayout = findViewById(R.id.meuLayout)
        val textView = findViewById<TextView>(R.id.textView)

        constraintLayout.setOnClickListener {
            showSnackbar(textView.text.toString())
        }
    }

    private fun showSnackbar(message: String) {
        // Inicia uma animação para alterar a cor de fundo do ConstraintLayout
        val colorFrom = resources.getColor(android.R.color.white)
        val colorTo = resources.getColor(android.R.color.darker_gray)

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 1000 // duração da animação em milissegundos

        colorAnimation.addUpdateListener { animator ->
            constraintLayout.setBackgroundColor(animator.animatedValue as Int)
        }

        colorAnimation.start()

        // Cria e exibe o Snackbar
        val snackbar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Fechar") {
            closeSnackbar(snackbar)
        }
        snackbar.show()
    }

    private fun closeSnackbar(snackbar: Snackbar) {

        // Inicia uma animação para restaurar a cor de fundo original do ConstraintLayout
        val colorFrom = resources.getColor(android.R.color.darker_gray)
        val colorTo = resources.getColor(android.R.color.white)

        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 1000 // duração da animação em milissegundos

        colorAnimation.addUpdateListener { animator ->
            constraintLayout.setBackgroundColor(animator.animatedValue as Int)
        }

        colorAnimation.start()

        // Fecha o Snackbar
        snackbar.dismiss()
    }
}

