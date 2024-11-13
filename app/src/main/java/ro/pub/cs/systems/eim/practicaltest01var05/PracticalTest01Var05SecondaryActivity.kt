package ro.pub.cs.systems.eim.practicaltest01var05

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

class PracticalTest01Var05SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var05_secondary)
        // Preluăm instrucțiunile transmise din MainActivity și le afișăm
        val instructions = intent.getStringExtra("INSTRUCTIONS")
        val instructionsTextView = findViewById<TextView>(R.id.instructions_text)
        instructionsTextView.text = instructions

        // Butonul Register - trimite rezultatul și închide activitatea
        findViewById<Button>(R.id.verify_button).setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("RESULT", "Verify")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        // Butonul Cancel - trimite rezultatul și închide activitatea
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("RESULT", "Cancel")
            setResult(Activity.RESULT_CANCELED, resultIntent)
            finish()
        }

    }
}


