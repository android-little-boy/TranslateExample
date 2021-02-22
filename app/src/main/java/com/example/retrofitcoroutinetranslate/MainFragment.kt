package com.example.retrofitcoroutinetranslate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainFragment : Fragment() {

    lateinit var textview: TextView
    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var word:String

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textview = view.findViewById(R.id.textview)
        editText = view.findViewById(R.id.editTextTextMultiLine)
        button = view.findViewById(R.id.button)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.translateResult.observe(viewLifecycleOwner) {
            textview.text = "原词:    $word \n翻译:    $it"
        }
        button.setOnClickListener {
            word = editText.text.toString()
            viewModel.translate(word)
        }

    }


}