/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example.yzz.openglwar.adapter;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import example.yzz.openglwar.episode1.ui.warepisode1.WarEpisode1_1Fragment;
import example.yzz.openglwar.episode1.ui.warepisode2.WarEpisode1_2Fragment;
import example.yzz.openglwar.episode1.ui.warepisode3.WarEpisode1_3Fragment;
import example.yzz.openglwar.episode1.ui.warepisode4.WarEpisode1_4Fragment;
import example.yzz.openglwar.episode2.ui.warepisode5.WarEpisode2_5Fragment;
import example.yzz.openglwar.episode2.ui.warepisode6.WarEpisode2_6Fragment;
import example.yzz.openglwar.episode2.ui.warepisode7.WarEpisode2_7Fragment;
import example.yzz.openglwar.episode2.ui.warepisode8.WarEpisode2_8Fragment;
import example.yzz.openglwar.episode3.ui.warepisode10.WarEpisode3_10Fragment;
import example.yzz.openglwar.episode3.ui.warepisode11.WarEpisode3_11Fragment;
import example.yzz.openglwar.episode3.ui.warepisode12.WarEpisode3_12Fragment;
import example.yzz.openglwar.episode3.ui.warepisode9.WarEpisode3_9Fragment;
import example.yzz.openglwar.episode4.ui.warepisode1.WarEpisode4_1Fragment;
import example.yzz.recyclerviewdemo.R;


/**
 * Holds the image resource references used by the grid and the pager fragments.
 */
abstract class FragmentData {


    static final Fragment[] FRAGMENT_WAR = {
            WarEpisode1_1Fragment.newInstance(),
            WarEpisode1_2Fragment.newInstance(),
            WarEpisode1_3Fragment.newInstance(),
            WarEpisode1_4Fragment.newInstance(),
            WarEpisode2_5Fragment.newInstance(),
            WarEpisode2_6Fragment.newInstance(),
            WarEpisode2_7Fragment.newInstance(),
            WarEpisode2_8Fragment.newInstance(),
            WarEpisode3_9Fragment.newInstance(),
            WarEpisode3_10Fragment.newInstance(),
            WarEpisode3_11Fragment.newInstance(),
            WarEpisode3_12Fragment.newInstance(),
            WarEpisode4_1Fragment.newInstance()
    };

    // Image assets (free for commercial use, no attribution required, from pixabay.com)
    @DrawableRes
    static final int[] FRAGMENT_DRAWABLES = {
            R.drawable.fragment1_1,
            R.drawable.fragment1_2,
            R.drawable.fragment1_3,
            R.drawable.fragment1_4,
            R.drawable.fragment1_5,
            R.drawable.fragment1_6,
            R.drawable.fragment1_7,
            R.drawable.fragment1_8,
            R.drawable.fragment1_9,
            R.drawable.fragment1_10,
            R.drawable.fragment1_11,
            R.drawable.fragment1_12,
            R.drawable.fragment1_12,
    };

    @StringRes
    static final int[] FRAGMENT_TEXT_DRAWABLES = {
            R.string.fragment_description1,
            R.string.fragment_description2,
            R.string.fragment_description3,
            R.string.fragment_description4,
            R.string.fragment_description5,
            R.string.fragment_description6,
            R.string.fragment_description7,
            R.string.fragment_description8,
            R.string.fragment_description9,
            R.string.fragment_description10,
            R.string.fragment_description11,
            R.string.fragment_description12,
            R.string.fragment_description1,
            R.string.fragment_description1,
            R.string.fragment_description1,
            R.string.fragment_description1,
            R.string.fragment_description1
    };


    // Image assets (free for commercial use, no attribution required, from pixabay.com)
    @DrawableRes
    static final int[] COLLECTION_DRAWABLES = {
            R.drawable.collect_grid_1,
            R.drawable.collect_grid_2,
            R.drawable.collect_grid_3,
            R.drawable.collect_grid_4,
            R.drawable.collect_grid_4,
            R.drawable.collect_grid_4
    };

    @StringRes
    static final int[] COLLECTION_TEXT_DRAWABLES = {
            R.string.war_description1,
            R.string.war_description2,
            R.string.war_description3,
            R.string.war_description4,
            R.string.war_description5,
            R.string.war_description6
    };

}
