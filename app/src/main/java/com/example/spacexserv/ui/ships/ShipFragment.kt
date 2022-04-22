package com.example.spacexserv.ui.ships

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.spacexserv.R
import com.example.spacexserv.databinding.FragmentShipBinding
import com.example.spacexserv.model.ships.Ships
import com.example.spacexserv.ui.MainActivity
import com.squareup.picasso.Picasso

class ShipFragment : MvpAppCompatFragment(), ShipView {

    private var _binding: FragmentShipBinding? = null
    private val binding get() = _binding!!

    @InjectPresenter
    lateinit var shipPresenter: ShipPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShipBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("id", "")
        Log.d("Artem","Ship id arrived $id")
        if (id != null){

            if (activity != null){
                (activity as MainActivity).supportActionBar?.title = id
            }
            shipPresenter.getShipById(id)
        }

    }

    override fun onLoading() {

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onError() {
        context?.let {
            Toast.makeText(it,"Error",Toast.LENGTH_LONG).show()
        }
    }

    override fun onLoaded(ship: Ships){

        Log.d("Artem","Building ship")
        Picasso.get().load(ship.image).into(binding.shipIv)
        binding.titleTv.text = ship.id
        binding.homePortTv.text = ship.home_port
        binding.typeTv.text = ship.type
        binding.weightTv.text = ship.weight_kg.toString()
        binding.yearBuildTv.text = ship.year_built.toString()
        binding.absTv.text = ship.abs.toString()

    }


}