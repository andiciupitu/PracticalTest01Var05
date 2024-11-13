package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ro.pub.cs.systems.eim.practicaltest01var05.ui.theme.PracticalTest01Var05Theme

class MainActivity : ComponentActivity() {
    private var cardinalPointsCount = 0
    private val REQUEST_CODE_SECONDARY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var05_main)
        val editText = findViewById<EditText>(R.id.editTextText)
        val topLeftButton = findViewById<Button>(R.id.topLeft_button)
        val topRightButton = findViewById<Button>(R.id.topRight_button)
        val bottomLeftButton = findViewById<Button>(R.id.bottomLeft_button)
        val bottomRightButton = findViewById<Button>(R.id.bottomRight_button)
        val centerButton = findViewById<Button>(R.id.center_button)


        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        val navigateButton = findViewById<Button>(R.id.secondary_button)
        if (savedInstanceState != null) {
            cardinalPointsCount = savedInstanceState.getInt("cardinalPointsCount", 0)
            Toast.makeText(this, "Total Button Presses: $cardinalPointsCount", Toast.LENGTH_LONG).show()
        }
        val buttonClickListener = { button: Button ->
            val buttonText = button.text.toString()
            // Concatenează textul la EditText
            editText.append(", $buttonText")
            cardinalPointsCount++
            counterTextView.text = "Cardinal Points Selected: $cardinalPointsCount"
        }
        topRightButton.setOnClickListener { buttonClickListener(topRightButton) }
        topLeftButton.setOnClickListener { buttonClickListener(topLeftButton) }
        bottomLeftButton.setOnClickListener { buttonClickListener(bottomLeftButton) }
        bottomRightButton.setOnClickListener { buttonClickListener(bottomRightButton) }
        centerButton.setOnClickListener { buttonClickListener(centerButton) }
        navigateButton.setOnClickListener {
            // Resetare EditText și contor

            val instructions = editText.text.toString()
            editText.setText("")
            cardinalPointsCount = 0
            counterTextView.text = "Cardinal Points Selected: 0"

            // Trimitere intenție pentru a deschide SecondaryActivity
            val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java)
            intent.putExtra("INSTRUCTIONS", instructions)
            startActivityForResult(intent, REQUEST_CODE_SECONDARY)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Salvăm numărul total de apăsări în Bundle
        outState.putInt("cardinalPointsCount", cardinalPointsCount)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SECONDARY) {
            val result = data?.getStringExtra("RESULT")
            if (resultCode == RESULT_OK && result == "Verify") {
                Toast.makeText(this, "Button pressed: Verify", Toast.LENGTH_SHORT).show()
            } else if (resultCode == RESULT_CANCELED && result == "Cancel") {
                Toast.makeText(this, "Button pressed: Cancel", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restaurăm valoarea numărului de apăsări și actualizăm afișarea
        cardinalPointsCount = savedInstanceState.getInt("cardinalPointsCount", 0)
        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        counterTextView.text = "Cardinal Points Selected: $cardinalPointsCount"

        // Afișăm un Toast cu valoarea restaurată
        Toast.makeText(this, "Total Button Presses: $cardinalPointsCount", Toast.LENGTH_LONG).show()
    }
}

