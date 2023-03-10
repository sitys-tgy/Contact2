package my.edu.tarc.contact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import my.edu.tarc.contact.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("First Fagment", "onViewCreated")

        //Attach adapter to the Recycle view
        val contactAdapter = ContactAdapter()

        with(binding.recycleViewContact){
            layoutManager = LinearLayoutManager(requireActivity())
            contactAdapter.setContact(MainActivity.contactList)
            adapter = contactAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("First Fagment", "onDestroyView")
    }
}