package com.example.bourbon.activities.harish_activities.helper_classes

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bourbon.activities.harish_activities.adapters.NewsAdapter
import com.example.bourbon.activities.harish_activities.model.DashboardCards
import com.example.bourbon.activities.harish_activities.model.NewsClassModel

class NewsApiHelper(private val ctx:Context,val products:ArrayList<NewsClassModel>,val adapter: NewsAdapter) {
    private val TAG = "NewsApiHelper"
    private val apiKey="ab1e7cf4b1534ec0a0f4f36589e81f18"


    fun fetchNews(){
        val requestQueue=Volley.newRequestQueue(ctx)

        val url = "http://newsapi.org/v2/top-headlines?country=in&apiKey=$apiKey"


        println("Reached fetchNews")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    println("""
                        Response: $response
                    """.trimIndent())

                    val articles=response.getJSONArray("articles")

                    for(i in 0..11){
                        val article=articles.getJSONObject(i)
                        val item=NewsClassModel(article.getString("title"),
                                article.getString("description"),
                                article.getString("author"))

                        products.add(item)
                        adapter.notifyItemInserted(i)
                        println("Adding Items: $item")
                    }


                },
                Response.ErrorListener { e ->
                    // TODO: Handle error
                    println("""
                        $TAG:Error:  ${e.message}
                    """.trimIndent())
                }
        )

        requestQueue.add(jsonObjectRequest)


    }
}