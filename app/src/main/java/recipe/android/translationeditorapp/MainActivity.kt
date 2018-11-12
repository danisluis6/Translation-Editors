package recipe.android.translationeditorapp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var mTextView: TextView? = null
    var mLanguage: Spinner? = null
    var mAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLanguage = findViewById<Spinner>(R.id.spLanguage) as Spinner
        mTextView = findViewById<TextView>(R.id.textView) as TextView
        mAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.language_option))
        mLanguage!!.setAdapter(mAdapter)

        when {
            LocalHelper.getLanguage(this@MainActivity).equals("en", ignoreCase = true) -> mLanguage!!.setSelection(mAdapter!!.getPosition("English"))
            LocalHelper.getLanguage(this@MainActivity).equals("in", ignoreCase = true) -> mLanguage!!.setSelection(mAdapter!!.getPosition("Indonesian"))
            else -> mLanguage!!.setSelection(mAdapter!!.getPosition("Spanish"))
        }

        mLanguage?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        LocalHelper.setLocale(this@MainActivity, "en")
                        mTextView!!.setText(getString(R.string.text_translation))
                    }
                    1 -> {
                        LocalHelper.setLocale(this@MainActivity, "in")
                        mTextView!!.setText(getString(R.string.text_translation))
                    }
                    2 -> {
                        LocalHelper.setLocale(this@MainActivity, "es")
                        mTextView!!.setText(getString(R.string.text_translation))
                    }
                }
            }

        }

    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocalHelper.onAttach(newBase))
    }
}
