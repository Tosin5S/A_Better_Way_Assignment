package com.x5s.a_better_way_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.x5s.a_better_way_assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpData(binding)
    }

    private fun setUpData(binding: ActivityMainBinding){
        binding.contactRv.adapter = adapter
        binding.contactRv.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextView>(R.id.nameEt)
        val no = view.findViewById<TextView>(R.id.numberEt)
        val saveBtn = view.findViewById<TextView>(R.id.saveBt)

        no.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                saveBtn.isEnabled = s?.length == 11

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        val alertDialog = builder.create()
        saveBtn.setOnClickListener{
            val contact = Contact(name.text.toString(),no.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContacts(contacts)
            alertDialog.dismiss()
        }


        binding.fab.setOnClickListener{
            alertDialog.show();
        }

    }
}