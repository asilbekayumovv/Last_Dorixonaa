package com.example.dorixona

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dorixona.databinding.FragmentLoginBinding
import com.example.dorixona.databinding.FragmentRegistrationBinding
import com.example.dorixona.util.ShPHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogIn.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogIn : Fragment() {
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
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        val userList = ShPHelper.getInstance(requireContext()).getUser()

        binding.textView5.setOnClickListener{
           findNavController().navigate(R.id.action_logIn_to_registration2)
        }
        val bundle = Bundle()
        binding.button.setOnClickListener {
            for (i in userList){
                if (i.status){
                    ShPHelper.getInstance(requireContext()).updateUser(i, false)
                }
                if (i.email == binding.email.text.toString()){
                    if (i.password == binding.parol.text.toString()){
                        bundle.putSerializable("user", i)
                        findNavController().navigate(R.id.action_logIn_to_main, bundle)
                        ShPHelper.getInstance(requireContext()).updateUser(i, true)
                    }
                }
                else{
                    Toast.makeText(requireContext(), "Iltimos boshqa email va parolni sinab ko'ring!", Toast.LENGTH_SHORT).show()
                }
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
         * @return A new instance of fragment register.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LogIn().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}