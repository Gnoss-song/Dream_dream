package kr.co.material.basic.total

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import kr.co.material.basic.R
import kr.co.material.basic.common.GirlGroupApplication
import kr.co.material.basic.common.GirlGroupRandomInit.getGirlGenerationName
import kr.co.material.basic.common.GirlGroupRandomInit.getTwiceName
import kr.co.material.basic.remainder.GirlGroupViewHolder
import java.util.*

class GirlGroupFinalRecyclerAdapter(private val girlsImages: ArrayList<Int>, private val owner: Activity, private val groupName: String) :
        RecyclerView.Adapter<GirlGroupViewHolder>() {
    private var slideInAnimation: Animation

    init {
        AnimationUtils.loadAnimation(GirlGroupApplication.getGirlGroupContext(),
                android.R.anim.slide_in_left).also { slideInAnimation = it }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.girls_group_recycler_item, parent, false)
        return GirlGroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GirlGroupViewHolder, position: Int) {
        val imageKey = girlsImages[position]
        var name = when (groupName) {
            "Twice" -> getTwiceName(imageKey)
            "girlGeneration" -> getGirlGenerationName(imageKey)
            else -> {
                getTwiceName(imageKey)
            }
        }
        if (name == "null") {
            name = getGirlGenerationName(imageKey)
        }
        with(holder) {
            with(memberTV) {
                text = name
                with(memberIV) {
                    setImageResource(imageKey)
                    startAnimation(slideInAnimation)
                }
            }

            itemRoot.setOnClickListener {
                val intent = Intent(owner, GirlsMemberDetailActivity::class.java)
                intent.putExtra("memberImage", girlsImages[position])
                intent.putExtra("memberName", holder.memberTV.text.toString())
                val girlsImagePair = Pair.create<View, String>(holder.memberIV, ViewCompat.getTransitionName(holder.memberIV))
                val girlsNamePair = Pair.create<View, String>(holder.memberTV, ViewCompat.getTransitionName(holder.memberTV))
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        owner, girlsImagePair, girlsNamePair)
                ActivityCompat.startActivity(owner, intent, options.toBundle())
            }
        }
    }

    override fun getItemCount() = girlsImages.size
}