package com.example.appmeuslivros

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.widget.ImageView

class PV_Adapter(var context:Context, var PVlivros:List<PV_livro>) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.pv_inflater, container, false)
            val img:ImageView = view.findViewById(R.id.imgLivros)
            img.setImageResource(PVlivros[position].imagem)
            container.addView(view)

            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view == obj
        }

        override fun getCount(): Int {
            return PVlivros.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return PVlivros[position].nome1
        }
    }
