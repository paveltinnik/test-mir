package com.paveltinnik.test_mir

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MenuItemRVAdapter(
    private val menuItemList: ArrayList<ButtonMenuItem>,
    private val context: Context
): RecyclerView.Adapter<MenuItemRVAdapter.MenuItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuItemRVAdapter.MenuItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.menu_item,
            parent, false
        )
        return MenuItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.menuItemImg.setImageResource(menuItemList.get(position).menuItemImg)
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuItemImg: ImageView = itemView.findViewById(R.id.menu_item)
    }
}