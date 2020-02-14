package tronku.project.zealicon.Fragment

import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_fragment.*
import tronku.project.zealicon.Adapter.TracksAdapter
import tronku.project.zealicon.Model.EventTrack

import tronku.project.zealicon.R
import tronku.project.zealicon.Utils.AnimUtils
import tronku.project.zealicon.Viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimUtils.setTouchEffect(day_one_list, R.id.action_home_to_playListFragment, null)
        AnimUtils.setTouchEffect(day_two_list)
        AnimUtils.setTouchEffect(day_three_list)
        AnimUtils.setTouchEffect(day_four_list)

        getUpcomingHits()
    }

    private fun getUpcomingHits() {
        val adapter = TracksAdapter()
        val trackList = ArrayList<EventTrack>()
        trackList.add(EventTrack(1, "Line up", "GPS based event", "12:00 PM", "4:00 PM", "Play it On!"))
        trackList.add(EventTrack(2, "Doodle", "Designing event", "2:00 PM", "5:00 PM", "Play it On!"))
        trackList.add(EventTrack(3, "Doodle", "Designing event", "2:00 PM", "5:00 PM", "Play it On!"))
        adapter.submitList(trackList)
        upcomingHitsRecycler.adapter = adapter
    }

}
