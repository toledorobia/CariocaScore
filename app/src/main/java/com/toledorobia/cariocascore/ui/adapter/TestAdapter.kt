package com.toledorobia.cariocascore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toledorobia.cariocascore.databinding.ItemGameListBinding
import com.toledorobia.cariocascore.domain.model.TestData

class TestAdapter: RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    private var items = mutableListOf<TestData>()

    fun updateItems(items: List<TestData>) {
        this.items = items.toMutableList();
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val binding = ItemGameListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            tvName.text = item.name
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TestViewHolder(val binding: ItemGameListBinding): RecyclerView.ViewHolder(binding.root)
}