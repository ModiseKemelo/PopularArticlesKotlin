package com.mkemelo.kotlinpopulararticles.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkemelo.kotlinpopulararticles.R
import com.mkemelo.kotlinpopulararticles.adapter.RecyclerViewAdapter
import com.mkemelo.kotlinpopulararticles.model.ArticlesListData
import com.mkemelo.kotlinpopulararticles.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val articlePeriods = resources.getStringArray(R.array.periods)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, articlePeriods)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + articlePeriods[position], Toast.LENGTH_SHORT).show()
                    loadArticlesData(articlePeriods[position].toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }
    }



    fun loadArticlesData(period: String) {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getArticlesDataObserver().observe(this, Observer<ArticlesListData>{ response ->
            println("Server resposnse ************************ $response")
            if(response != null) {
                recyclerViewAdapter.setListData(response.results)
                recyclerViewAdapter.notifyDataSetChanged()

            } else {
                Toast.makeText(this@MainActivity, "Failed to load articles data in the past $period days", Toast.LENGTH_LONG).show()
            }

        })
            viewModel.fetchArticlesDataFromServer(period)

    }
}