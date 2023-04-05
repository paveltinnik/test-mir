package com.paveltinnik.test_mir

data class ButtonMenuItem(
    var menuItemImg: Int
)

val buttonMenuItemData: ArrayList<ButtonMenuItem> = arrayListOf(
    ButtonMenuItem(R.drawable.settings),
    ButtonMenuItem(R.drawable.home),
    ButtonMenuItem(R.drawable.search),
    ButtonMenuItem(R.drawable.time),
    ButtonMenuItem(R.drawable.layout)
)
