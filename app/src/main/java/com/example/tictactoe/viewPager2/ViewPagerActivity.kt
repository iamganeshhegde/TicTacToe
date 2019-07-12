package com.example.tictactoe.viewPager2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_viewpager.*
import java.util.ArrayList

class ViewPagerActivity : AppCompatActivity() ,View.OnClickListener{

    var TAG = ViewPagerActivity::class.java.canonicalName
    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.ivDown -> changeViewPager()
        }


    }

    private fun changeViewPager() {


        if(viewPager.currentItem +1 !=null)
        {
            viewPager.currentItem = viewPager.currentItem+1;

        }


       /* viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Log.e("Selected_Page", String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });*/
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_viewpager)


        ivDown.setOnClickListener(this)


        val list = ArrayList<String>()
        list.add("First Screen")
        list.add("Second Screen")
        list.add("Third Screen")


        val welcomeAdapter = WelcomeAdapter(this, list, viewPager)

        viewPager.setAdapter(welcomeAdapter)



        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)


            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)


                Log.i(TAG+": scrolled",position.toString())

            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                Log.i(TAG+" selected",position.toString())
                Toast.makeText(this@ViewPagerActivity,position.toString(),Toast.LENGTH_SHORT).show()
            }

        })

    }
}
