package my.edu.tarc.contact

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import my.edu.tarc.contact.databinding.FragmentAddContactBinding
import my.edu.tarc.contact.model.Contact

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddContactFragment : Fragment(), MenuProvider {
    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        Log.d("Add Fragment", "onViewCreated")
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.findItem(R.id.action_settings).isVisible = false
        menu.findItem(R.id.action_profile).isVisible = false
        menu.findItem(R.id.action_save).isVisible = true
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == R.id.action_save){
            // TODO: Insert a new contact record to the contactList
            //Validate input data
            if(binding.editTextAddName.text.isNullOrEmpty()){
                binding.editTextAddName.setError(getString(R.string.invalid_input))
                return false
            }

            if(!Helper().isValidPhone(binding.editTextAddPhone.text.toString())){
                binding.editTextAddPhone.setError(getString(R.string.invalid_input))
                return false
            }

            if(!Helper().isValidEmail(binding.editTextAddEmail.text.toString())){
                binding.editTextAddEmail.setError(getString(R.string.invalid_input))
                return false
            }

            val contact = Contact(
                binding.editTextAddName.text.toString(),
                binding.editTextAddPhone.text.toString(),
                binding.editTextAddEmail.text.toString()
            )

            MainActivity.contactList.add(contact)
            Snackbar.make(
                this.requireActivity().findViewById(R.id.addContactLayout),
                getString(R.string.record_save),
                Snackbar.LENGTH_SHORT
            ). show()
            findNavController().navigateUp()
        }else if(menuItem.itemId == android.R.id.home){
            // To handle the Up button
            findNavController().navigateUp()
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Add Fragment", "OnDestroyView")
    }

}