package com.example.dorixona

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dorixona.databinding.FragmentRegistrationBinding
import com.example.dorixona.model.User
import com.example.dorixona.util.ShPHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Registration.newInstance] factory method to
 * create an instance of this fragment.
 */
class Registration : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val shPHelper = ShPHelper.getInstance(requireContext())
        var user: User
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        val bundle = Bundle()
        binding.button.setOnClickListener {
            if (binding.ism.text.isNullOrEmpty() &&
                binding.familiya.text.isNullOrEmpty() &&
                binding.email.text.isNullOrEmpty() &&
                binding.parol.text.isNullOrEmpty() &&
                binding.parolRe.text.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Iltimos barcha bo'shliqlarni to'ldiring!", Toast.LENGTH_SHORT).show()
            }
            if (binding.parol.text!!.equals(binding.parolRe.text)){
                Toast.makeText(requireContext(), "Parolni qayta kirgazing!", Toast.LENGTH_SHORT).show()
            }
            else{
                user = User(binding.ism.text.toString(), binding.familiya.text.toString(), binding.email.text.toString(), binding.parol.text.toString())
                shPHelper.setUser(user)
                bundle.putSerializable("user", user)
                findNavController().navigate(R.id.action_registration2_to_main, bundle)
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Registration.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Registration().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}