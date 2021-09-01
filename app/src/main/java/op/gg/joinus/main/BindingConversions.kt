package op.gg.joinus.main

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url:String?){
        if(url != null){
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }
}