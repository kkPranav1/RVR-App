package com.matrimony.rvrmatrimony.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matrimony.rvrmatrimony.databinding.ItemSimpleSpinnerBinding

class CustomSpinnerAdapter(
    // private val ctx: Context,
    private var items: List<SpinnerItemPojo> = listOf(
        SpinnerItemPojo("No Options", 0)
    ),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<CustomSpinnerAdapter.SpinnerViewHolder>() {

    private var selectedItem: SpinnerItemPojo = items[0]
    var selectionMade: Boolean = false

    inner class SpinnerViewHolder(private val binding: ItemSimpleSpinnerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SpinnerItemPojo) {
            binding.itemtxt = item.spinnerText
            if (item.spinnerImageStatus == 1 || item.spinnerImageStatus == 2) {
                binding.ivMainItemIcon.visibility = View.VISIBLE
                if (item.spinnerImageStatus == 1)
                    binding.ivMainItemIcon.setImageResource(item.spinnerImageStatus)
                else {
                    // Need to Fetch Image from URL
                }
            }

            binding.root.setOnClickListener {
                selectedItem = item
                selectionMade = true
                // notifyDataSetChanged()
                onItemSelected(item.spinnerText)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpinnerViewHolder {
        val binding =
            ItemSimpleSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpinnerViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SpinnerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // Function to get the currently selected item
    fun getSelectedItem(): SpinnerItemPojo {
        return selectedItem
    }
}