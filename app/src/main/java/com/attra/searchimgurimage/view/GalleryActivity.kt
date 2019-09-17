package com.attra.searchimgurimage.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.attra.searchimgurimage.R
import com.attra.searchimgurimage.adapter.GalleryAdapter
import com.attra.searchimgurimage.service.ApiService
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Attra Infotech...!
 * Created by Awnish Kumar on 15/09/2019.
 */
class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        searchButton.setOnClickListener(View.OnClickListener {
            val searchText: String = searchImages.text.toString()
            val apiService = ApiService()
            val context = this as Context
            recyclerView.layoutManager = LinearLayoutManager(context)
            GlobalScope.launch(Dispatchers.Main) {

                val apiResponse = apiService.getImageData(searchText).await()
                System.out.println("Api resp....." + apiResponse.data.size)

                if (apiResponse.data.equals(null) || apiResponse.data.size == 0) {
                    Toast.makeText(context, "No Result Found", Toast.LENGTH_LONG).show()
                }
                when (recyclerView != null && apiResponse.status == 200) {
                    true -> recyclerView.adapter = GalleryAdapter(context, apiResponse.data)

                    false -> Log.v("VMA", "Failed " + apiResponse.status)
                }
            }
        })
    }

}
