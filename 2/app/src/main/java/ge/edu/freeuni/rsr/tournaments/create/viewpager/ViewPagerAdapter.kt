package ge.edu.freeuni.rsr.tournaments.create.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/*
* created by tgeldiashvili on 7/5/2019
*/

class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val FRAGMENT_CONFIG = 0
        const val FRAGMENT_QUESTION = 1
    }

    override fun getItem(position: Int): Fragment {
        return if (position == FRAGMENT_CONFIG)
            TournamentConfigFragment.newInstance()
        else
            QuestionInputFragment.newInstance()
    }

    override fun getCount(): Int {
        return 2
    }
}