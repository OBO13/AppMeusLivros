package com.example.appmeuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pvactivity.*

class PVactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pvactivity)

        viewpager.adapter = PV_Adapter(this, PV_livro.getListaLivrosclassicos())
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

            override fun onPageScrollStateChanged(state: Int) {
                Log.i("LIVRO", "onPageScrollStateChanged");
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.i("LIVRO", "onPageScrolled chamado, posição: "+position);
            }

            override fun onPageSelected(position: Int) {
                Log.i("LIVRO", "onPageSelected chamado, posição: "+position);
            }

        })
    }
}