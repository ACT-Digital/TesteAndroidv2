package academy.mukandrew.bank.presenter.home

import academy.mukandrew.bank.R
import academy.mukandrew.bank.domain.models.Statement
import academy.mukandrew.bank.domain.models.UserInfo
import academy.mukandrew.bank.presenter.BankActivity
import academy.mukandrew.bank.presenter.auth.AuthViewModel
import academy.mukandrew.bank.presenter.home.list.StatementAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initViews()
        initLiveData()
    }

    private fun initListeners() {
        logoutButton.setOnClickListener(onLogoutClicked)
    }

    private val onLogoutClicked = View.OnClickListener {
        homeViewModel.postLogout()
    }

    private fun initViews() {
        statementList.adapter = StatementAdapter()
    }

    private fun initLiveData() {
        with(homeViewModel) {
            currentUser.observe(viewLifecycleOwner, bindUserInformation)
            statementList.observe(viewLifecycleOwner, bindList)
            logoutResult.observe(viewLifecycleOwner, bindLogout)
        }
    }

    private val bindUserInformation = Observer { user: UserInfo ->
        userNameTextView.text = user.name
        accountNumberTextView.text = user.getBankNumber()
        accountBalanceTextView.text = user.getBalanceFormatted()
    }

    private val bindLogout = Observer<Boolean> {
        (requireActivity() as BankActivity).goToAuthPage()
    }

    private val bindList = Observer { list: List<Statement> ->
        (statementList.adapter as? StatementAdapter)?.submitList(list)
    }
}