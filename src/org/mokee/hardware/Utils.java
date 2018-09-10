/*
 * Copyright (C) 2018 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mokee.hardware;

import android.util.Range;

import mokee.hardware.DisplayMode;
import mokee.hardware.HSIC;

import java.util.ArrayList;

class Utils {
    public static DisplayMode[] HIDLModeListToArray(
            ArrayList<vendor.mokee.livedisplay.V1_0.DisplayMode> modes) {
        int size = modes.size();
        DisplayMode[] r = new DisplayMode[size];
        for (int i = 0; i < size; i++) {
            vendor.mokee.livedisplay.V1_0.DisplayMode m = modes.get(i);
            r[i] = new DisplayMode(m.id, m.name);
        }
        return r;
    }

    public static DisplayMode fromHIDLMode(vendor.mokee.livedisplay.V1_0.DisplayMode mode) {
        return new DisplayMode(mode.id, mode.name);
    }

    public static HSIC fromHIDLHSIC(vendor.mokee.livedisplay.V1_0.HSIC hsic) {
        return new HSIC((float) hsic.hue, hsic.saturation, hsic.intensity,
                hsic.contrast, hsic.saturationThreshold);
    }

    public static vendor.mokee.livedisplay.V1_0.HSIC toHIDLHSIC(HSIC hsic) {
        vendor.mokee.livedisplay.V1_0.HSIC h = new vendor.mokee.livedisplay.V1_0.HSIC();
        h.hue = (int) hsic.getHue();
        h.saturation = hsic.getSaturation();
        h.intensity = hsic.getIntensity();
        h.contrast = hsic.getContrast();
        h.saturationThreshold = hsic.getSaturationThreshold();
        return h;
    }

    public static Range<Integer> fromHIDLRange(vendor.mokee.livedisplay.V1_0.Range range) {
        return new Range(range.min, range.max);
    }

    public static Range<Float> fromHIDLIntRange(vendor.mokee.livedisplay.V1_0.Range range) {
        return new Range((float) range.min, (float) range.max);
    }

    public static Range<Float> fromHIDLRange(vendor.mokee.livedisplay.V1_0.FloatRange range) {
        return new Range(range.min, range.max);
    }
}
