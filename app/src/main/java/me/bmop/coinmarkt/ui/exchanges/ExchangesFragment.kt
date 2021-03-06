package me.bmop.coinmarkt.ui.exchanges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_exchanges.group_loading
import kotlinx.android.synthetic.main.fragment_exchanges.recycler_view
import kotlinx.android.synthetic.main.fragment_exchanges.swipeRefreshLayout
import kotlinx.coroutines.launch

import me.bmop.coinmarkt.R
import me.bmop.coinmarkt.ui.adapter.exchanges.ExchangesAdapter
import me.bmop.coinmarkt.ui.base.ScopeFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ExchangesFragment : ScopeFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: ExchangesViewModelFactory by instance()
    private lateinit var viewModel: ExchangesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exchanges, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ExchangesViewModel::class.java)

        bindUI()

        swipeRefreshLayout.setOnRefreshListener {
            bindUI()
        }
    }

    private fun bindUI() = launch {
        val exchanges = viewModel.getExchanges()
        exchanges.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            recycler_view.adapter =
                ExchangesAdapter(it)
            recycler_view.layoutManager = LinearLayoutManager(parentFragment?.context)
            recycler_view.setHasFixedSize(true)
            group_loading.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ExchangesFragment().apply {}
    }
}
