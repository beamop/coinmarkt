package me.bmop.coinmarkt.ui.fragment.cc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.news_item.*
import kotlinx.coroutines.launch

import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.ui.adapter.cc.NewsAdapter
import me.bmop.coinmarkt.ui.base.ScopeFragment
import me.bmop.coinmarkt.ui.viewmodel.cc.news.NewsViewModel
import me.bmop.coinmarkt.ui.viewmodel.cc.news.NewsViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class NewsFragment : ScopeFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: NewsViewModelFactory by instance()
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val news = viewModel.getNews()
        news.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            recycler_view.adapter = NewsAdapter(it.toList())
            recycler_view.layoutManager = LinearLayoutManager(parentFragment?.context)
            recycler_view.setHasFixedSize(true)
            progressBar_loading.visibility = View.GONE
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NewsFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
