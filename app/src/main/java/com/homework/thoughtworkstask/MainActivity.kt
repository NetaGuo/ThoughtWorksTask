package com.homework.thoughtworkstask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.homework.thoughtworkstask.adapter.TwitterAdapter
import com.homework.thoughtworkstask.entity.TwitterItem
import com.homework.thoughtworkstask.entity.User
import com.homework.thoughtworkstask.recycler.ListFooterType
import com.homework.thoughtworkstask.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<MainViewModel>()
    private var page = 1
    private lateinit var adapter: TwitterAdapter

    companion object {
        //Support for small amounts of data
        var allData = mutableListOf<TwitterItem>()
        // SP FIle or others
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindLiveData()
        vm.getUserData()
        vm.getTwittersData()
    }


    private fun bindLiveData() {
        vm.userData.observe(this) { result ->
            result.onSuccess {
                showHeadUI(it)
            }.onFailure {

            }
        }
        vm.twitters.observe(this) { result ->
            result.onSuccess { list ->
                if (list.isNullOrEmpty()) {
                    //show empty UI
                } else {
                    allData.addAll(list.filter {
                        it.error.isNullOrBlank() && !it.content.isNullOrBlank() && !it.images.isNullOrEmpty()
                    })
                    showTwitters()
                }
            }.onFailure { }
        }
    }

    private fun showHeadUI(data: User) {
        Glide.with(this).load(data.profileImage).placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image).into(ivBg)
        tvNickNameNow.text = data.nick
        Glide.with(this).load(data.avatar).placeholder(R.drawable.ic_avatar)
            .error(R.drawable.ic_avatar).into(ivAvatarNow)
    }

    private fun showTwitters() {

        //the basic load more no encapsulation yet
        fun loadMore(){

            if (adapter.itemCount < allData.size){
                val toIndex = if ((page*5+5) >= allData.size) allData.size else page*5+5
                adapter.addData(allData.subList(page*5,toIndex))
                page++
            } else {
                adapter.setFootType(ListFooterType.FOOT_NO_LOADDATA)
            }
        }
        val more = {
            loadMore()
        }
        adapter = TwitterAdapter(this, more)
        adapter.setEnabledFoot(true)
        lrvTwitter.init(adapter,more)
        adapter.setTrueData(allData.subList(0, 5))
    }

}
