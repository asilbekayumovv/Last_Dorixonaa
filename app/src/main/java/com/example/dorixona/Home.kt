package com.example.dorixona

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import androidx.viewpager2.widget.ViewPager2
import com.example.dorixona.adapter.BookAdapter
import com.example.dorixona.adapter.BookPageAdapter
import com.example.dorixona.adapter.FilterAdapter
import com.example.dorixona.databinding.FragmentHomeBinding
import com.example.dorixona.model.Books
import com.example.dorixona.model.Filter
import com.example.dorixona.model.book_pager


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var listRoman: MutableList<Books>
    private lateinit var listFilter: MutableList<Filter>
    private lateinit var listDarslik: MutableList<Books>
    private lateinit var listQissa: MutableList<Books>
    private lateinit var listPagerBook: MutableList<book_pager>

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
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.search.addTextChangedListener {
            Search.newInstance(it.toString())
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, Search()).commit()
        }
        listFilter = mutableListOf()
        listRoman = mutableListOf()
        listDarslik = mutableListOf()
        listQissa = mutableListOf()
        listPagerBook = mutableListOf()
        LoadFilter()
        LoadRoman()
        LoadDarslik()
        LoadQissa()
        LoadPagerBook()

        val viewPager = BookPageAdapter(listPagerBook, requireContext())
        binding.viewPager2.adapter = viewPager

        val handler = Handler()
        var origPosition: Int = 0
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val runnable = Runnable { binding.viewPager2.currentItem = position + 1 }
                if (position < (binding.viewPager2.adapter?.itemCount ?: 0)) {
                    handler.postDelayed(runnable, 3000)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == SCROLL_STATE_DRAGGING) handler.removeMessages(0)
            }
        })
        binding.viewPager2.setOnClickListener {
            handler.removeMessages(0)

            binding.viewPager2.currentItem = ++binding.viewPager2.currentItem
        }

        val filter = FilterAdapter(listFilter, object : FilterAdapter.MyBook {
            override fun onItemClick(filter: Filter) {
                val bundle = bundleOf("name" to filter)
                findNavController().navigate(R.id.action_main_to_romanFragment2, bundle)
            }
        }, requireContext())

        binding.filter.adapter = filter
        binding.filter.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter = BookAdapter(listRoman, object : BookAdapter.MyBook {
            override fun onItemClick(book: Books) {
                val bundle = bundleOf("book" to book)
                findNavController().navigate(R.id.action_main_to_moreFragment, bundle)
            }
        }, requireContext())

        binding.roman.adapter = adapter
        binding.roman.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter2 = BookAdapter(listDarslik, object : BookAdapter.MyBook {
            override fun onItemClick(book: Books) {
                val bundle = bundleOf("book" to book)
                findNavController().navigate(R.id.action_main_to_moreFragment, bundle)
            }
        }, requireContext())
        binding.darslik.adapter = adapter2
        binding.darslik.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter3 = BookAdapter(listQissa, object : BookAdapter.MyBook {
            override fun onItemClick(book: Books) {
                val bundle = bundleOf("book" to book)
                findNavController().navigate(R.id.action_main_to_moreFragment, bundle)
            }
        }, requireContext())
        binding.qissalar.adapter = adapter3
        binding.qissalar.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        return binding.root
    }

    private fun LoadRoman() {
        listRoman.add(
            Books(
                "O'tkan kunlar",
                "Abdulla Qodiriy",
                R.drawable.utkan_kunlar,
                roman = true
            )
        )
        listRoman.add(
            Books(
                "Ufq",
                "Said Ahmad",
                R.drawable.ufq,
                roman = true
            )
        )
        listRoman.add(
            Books(
                "Manaschi",
                "Abdulhamid Ismoil",
                R.drawable.manaschi,
                roman = true
            )
        )
        listRoman.add(
            Books(
                "Sarob",
                "Abdulla Qahhor",
                R.drawable.sarob,
                roman = true
            )
        )
    }

    private fun LoadDarslik() {
        listDarslik.add(
            Books(
                "Matematika",
                "6-sinf",
                R.drawable.matem,
                darslik = true
            )
        )
        listDarslik.add(
            Books(
                "Fizika",
                "7-sinf",
                R.drawable.fizika,
                darslik = true
            )
        )
    }

    private fun LoadQissa() {
        listQissa.add(
            Books(
                "Dunyoning ishlari",
                "O'tkir Hoshimov",
                R.drawable.dunyoning_ishlari,
                qissa = true
            )
        )
        listQissa.add(
            Books(
                "Qariya",
                "Abbos Said",
                R.drawable.apple,
                darslik = true
            )
        )
    }

    private fun LoadFilter() {
        listFilter.add(Filter("Barchasi", state = true))
        listFilter.add(Filter("Darsliklar"))
        listFilter.add(Filter("Diniy kitoblar"))
        listFilter.add(Filter("Bepul kitoblar"))
        listFilter.add(Filter("Romanlar"))
        listFilter.add(Filter("Qissalar"))
    }

    private fun LoadPagerBook() {
        listPagerBook.add(book_pager("Ufq romani", R.drawable.ufq))
        listPagerBook.add(book_pager("O'tkan kunlar romani", R.drawable.utkan_kunlar))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}