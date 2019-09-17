package com.attra.searchimgurimage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attra.myimage.model.Data
import com.attra.searchimgurimage.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_list.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Attra Infotech...!
 * Created by Awnish Kumar on 15/09/2019.
 */

class GalleryAdapter(
    private val context: Context?,
    private var imageDetailsList: List<Data>
) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return imageDetailsList.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0?.context)
        val cellForRow = layoutInflater.inflate(R.layout.image_list, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        val imageDetails = imageDetailsList[p1]
        p0.view.title.text = imageDetails.title

        if ((imageDetails.images != null)) {
            p0.view.noOfImages.text = imageDetails.images.size.toString()
            val myDate = SimpleDateFormat("dd/MM/yyyy  HH:mm:ss")
                .format(Date(imageDetails.datetime * 1000L))
            p0.view.imageDate.text = myDate.toString()

            Picasso
                .with(context) // give it the context
                .load(imageDetails.images[0].link) // load the image
                .into(p0.view.photo)
        }


    }
}


class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}
