package com.bauman.personlistsample.ui.person_details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.bauman.personlistsample.R
import com.bauman.personlistsample.data.DataGenerator
import com.bauman.personlistsample.data.ViewTyped.Person
import com.bauman.personlistsample.databinding.ActivityPersonDetailsBinding

class PersonDetailsActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context, personPosition: Int): Intent {
            return Intent(context, PersonDetailsActivity::class.java).apply {
                putExtra(EXTRA_PERSON_POSITION, personPosition)
            }
        }

        private const val EXTRA_PERSON_POSITION = "EXTRA_PERSON_POSITION"
    }

    private lateinit var binding: ActivityPersonDetailsBinding
    private var personPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleExtra()
    }

    private fun handleExtra() {
        personPosition = intent.getIntExtra(EXTRA_PERSON_POSITION, -1)

        if (personPosition == -1) {
            handleError()
        } else {
            val item = DataGenerator.generatedData[personPosition] as? Person
            if (item != null) {
                setupPerson(item)
            } else {
                handleError()
            }
        }
    }

    private fun setupPerson(person: Person) {
        with(binding) {
            personImage.setImageResource((person.image ?: R.drawable.person_default))
            personName.text = "Name: ${person.name}"
            personAge.text = "Age: ${person.age}"
            personAddress.text = "Address: ${person.address}"
            personNumber.apply {
                text = "Number: ${person.phoneNumber}"
                setOnClickListener {
                    startActivity(
                        Intent(Intent.ACTION_CALL, Uri.parse("tel:${person.phoneNumber}"))
                    )
                }
            }
        }
    }

    private fun handleError() {
        makeText(this, "Error occurred!", LENGTH_SHORT).show()
        finish()
    }
}