package com.mkemelo.kotlinpopulararticles.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mkemelo.kotlinpopulararticles.R
import com.mkemelo.kotlinpopulararticles.adapter.RecyclerViewAdapter
import com.mkemelo.kotlinpopulararticles.databinding.ActivityMainBinding
import com.mkemelo.kotlinpopulararticles.model.ArticlesListData
import com.mkemelo.kotlinpopulararticles.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView;
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter;
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val articlePeriods = resources.getStringArray(R.array.periods)
        val spinner = binding.spinner
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
        recyclerView = binding.recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }
    }



    fun loadArticlesData(period: String) {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getArticlesDataObserver().observe(this, Observer<ArticlesListData>{ response ->
            // println("Server resposnse ************************ $response")
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