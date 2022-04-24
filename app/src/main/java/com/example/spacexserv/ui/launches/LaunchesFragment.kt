package com.example.spacexserv.ui.launches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.spacexserv.adapter.ShipsAdapter
import com.example.spacexserv.databinding.FragmentLaunchesBinding
import com.example.spacexserv.model.launches.Ship
import com.example.spacexserv.model.ships.Ships
import com.example.spacexserv.ui.MainActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class LaunchesFragment : MvpAppCompatFragment(), LaunchesView {

    private var _binding: FragmentLaunchesBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { ShipsAdapter() }

    @InjectPresenter
    lateinit var launchesPresenter: LaunchesPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null){
            (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLaunchesBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = adapter
        lifecycle.addObserver(binding.youtubePlayerView)

        binding.floatActBtn.setOnClickListener {  }

        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchesPresenter.startInitialization(context)
    }

    override fun onVideoSetup(link: String) {
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(link, 0f)
            }
        })
    }

    override fun onListSetup(ships: List<Ship>) {
        adapter.setList(ships as ArrayList<Ships>)
    }

    override fun addShip(ship: Ships) {
        adapter.addShip(ship)
        Log.d("Artem","Ship sending from fragment ${ship.id}")
    }

    override fun onConnectionAbsence() {
        context.let {
            Toast.makeText(
                it,
                "Error, connection lost", Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onUnknownError() {
        context.let {
            Toast.makeText(
                it,
                "Error", Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        launchesPresenter.dispose()
        super.onDestroyView()
    }

}