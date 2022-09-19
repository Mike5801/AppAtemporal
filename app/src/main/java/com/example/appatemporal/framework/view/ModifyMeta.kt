package com.example.appatemporal.framework.view
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import com.example.appatemporal.databinding.FormularioMetaBinding
import com.example.appatemporal.domain.Repository
import com.example.appatemporal.framework.view.DeleteActivity
import com.example.appatemporal.framework.view.PresupuestoAndMeta
import com.example.appatemporal.framework.viewModel.PresupuestoOrganizadorViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModifyMeta :BottomSheetDialogFragment() {
    private lateinit var binding: FormularioMetaBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments
        val idP = data!!.get("idProyecto").toString()
        val ganancia = data!!.get("ganancia").toString()
        val presupuesto = data!!.get("presupuesto").toString()
        val meta = data!!.get("meta").toString()
        val repository = Repository(requireContext())
        binding.descMeta.hint="Meta Actual: "+ meta


        binding.saveButtonMeta.setOnClickListener {
            val taskViewModel = PresupuestoOrganizadorViewModel()
            val meta = binding.descMeta.text.toString().toDouble()
            CoroutineScope(Dispatchers.IO).launch {

                taskViewModel.updateMeta(meta, idP.toInt(), repository)

            }
            val intent = Intent(requireContext(), PresupuestoAndMeta::class.java)
            with(intent) {
                putExtra("id_proyecto", idP.toInt())
                putExtra("gananciaK", ganancia.toDouble())
                putExtra("presupuestoK", presupuesto.toDouble())
                putExtra("presupuestoK", meta)
            }
            startActivity(intent)
            saveAction()
        }
        

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FormularioMetaBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction() {
        binding.descMeta.setText("")
        dismiss()
    }
}