package me.bmop.coinmarkt.ui.cryptocurrencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cryptocurrencies.*
import kotlinx.coroutines.*

import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.ui.adapter.CryptocurrenciesAdapter
import me.bmop.coinmarkt.ui.base.ScopeFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CryptocurrenciesFragment : ScopeFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CryptocurrenciesViewModelFactory by instance()
    private lateinit var viewModel: CryptocurrenciesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cryptocurrencies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CryptocurrenciesViewModel::class.java)

        bindUI()

        swipeRefreshLayout.setOnRefreshListener {
            bindUI()
        }
    }

    private fun bindUI() = launch {
        val markets = viewModel.getCryptocurrencies()
        markets.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
            recycler_view.adapter = CryptocurrenciesAdapter(it)
            recycler_view.layoutManager = LinearLayoutManager(parentFragment?.context)
            recycler_view.setHasFixedSize(true)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CryptocurrenciesFragment().apply {}
    }
}
