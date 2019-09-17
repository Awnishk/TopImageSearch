package com.attra.myimage.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.attra.myimage.adapter.GalleryAdapter
import com.attra.myimage.service.ApiService
import com.attra.searchimgurimage.R
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
            val context = this as Context;
            recyclerView.layoutManager = LinearLayoutManager(context)
            GlobalScope.launch(Dispatchers.Main) {

                val apiResponse = apiService.getImageData(searchText).await()

                if (apiResponse.data.equals(null) || apiResponse.data.size.equals("0")) {
                    errorMessgae.visibility= View.VISIBLE
                }
                when (recyclerView != null && apiResponse.status == 200) {
                    true -> recyclerView.adapter = GalleryAdapter(context, apiResponse.data)

                    false -> Log.v("VMA", "Failed " + apiResponse.status)
                }
            }
        })
    }

}