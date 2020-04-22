package com.yabahddou.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.yabahddou.BasicApp

/**
 * Helper extension properties for fragments and activities
 * to facilitate access to dependencies in the dagger graph.
 */

val Activity.injector
    get() = (application as BasicApp).component

val Fragment.injector
    get() = (requireActivity().application as BasicApp).component