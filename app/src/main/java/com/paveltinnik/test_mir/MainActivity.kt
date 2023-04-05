package com.paveltinnik.test_mir

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var menuButton: Button
    lateinit var menuItemRV: RecyclerView
    lateinit var menuItemRVAdapter: MenuItemRVAdapter
    lateinit var menuItemList: ArrayList<ButtonMenuItem>
    var isMenuHide: Boolean = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuButton= findViewById(R.id.menu_button)
        menuItemRV = findViewById(R.id.menu_recycler_view)
        menuItemList = buttonMenuItemData

        menuItemRV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, true)
        menuItemRV.visibility = View.GONE

        menuItemRVAdapter = MenuItemRVAdapter(menuItemList, this)
        menuItemRV.adapter = menuItemRVAdapter

        menuButton.setOnClickListener {
            if (isMenuHide) {
                menuItemRV.visibility = View.VISIBLE
                runLayoutAnimation(menuItemRV, isMenuHide)
                isMenuHide = false
            } else {
                runLayoutAnimation(menuItemRV, isMenuHide)
                isMenuHide = true
            }
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                menuItemList.removeAt(viewHolder.adapterPosition)
                menuItemRVAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(menuItemRV)
    }
}

fun runLayoutAnimation(recyclerView: RecyclerView, isMenuSlideToTop: Boolean) {
    val context = recyclerView.context
    val layoutAnimationController: LayoutAnimationController

    if (isMenuSlideToTop) {
        recyclerView.animate()
            .translationY(0F)
            .alpha(1.0F)

        layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_to_top)
    } else {
        recyclerView.animate()
            .translationY(recyclerView.height.toFloat())
            .alpha(0.0F)

        layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_to_bottom)
    }

    recyclerView.layoutAnimation = layoutAnimationController
    recyclerView.adapter?.notifyDataSetChanged()
}