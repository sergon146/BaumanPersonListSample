package com.bauman.personlistsample.ui.person_details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bauman.personlistsample.ui.PersonViewModelFactory
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
    private lateinit var viewModel: PersonDetailsViewModel

    private var personPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        handleExtra(savedInstanceState)
    }

    private fun initViewModel() {
        val factory = PersonViewModelFactory(DataGenerator)
        viewModel = ViewModelProvider(this, factory)[PersonDetailsViewModel::class.java]
        viewModel.person.observe(this, ::setupPerson)
        viewModel.error.observe(this) {
            Toast.makeText(this, it, LENGTH_SHORT).show()
        }
    }

    private fun handleExtra(savedInstanceState: Bundle?) {
        personPosition = savedInstanceState?.getInt(EXTRA_PERSON_POSITION, -1)
            ?: intent.getIntExtra(EXTRA_PERSON_POSITION, -1)
        viewModel.loadPersonDetails(personPosition)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState);
        if (personPosition != -1) {
            outState.putInt(EXTRA_PERSON_POSITION, personPosition);
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
       handleExtra(savedInstanceState)
    }
}