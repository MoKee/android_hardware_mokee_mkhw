/*
 * Copyright (C) 2016 The CyanogenMod Project
 * Copyright (C) 2015 The MoKee Open Source Project
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

import org.mokee.internal.util.FileUtils;

import android.util.Log;

public class VibratorHW {

    private static final String TAG = "VibratorHW";

    private static final String DEFAULT_PATH = "/sys/class/timed_output/vibrator/vtg_default";
    private static final String LEVEL_PATH = "/sys/class/timed_output/vibrator/vtg_level";
    private static final String MAX_PATH = "/sys/class/timed_output/vibrator/vtg_max";
    private static final String MIN_PATH = "/sys/class/timed_output/vibrator/vtg_min";

    public static boolean isSupported() {
        return FileUtils.isFileReadable(LEVEL_PATH) &&
                FileUtils.isFileWritable(LEVEL_PATH) &&
                FileUtils.isFileReadable(DEFAULT_PATH) &&
                FileUtils.isFileReadable(MAX_PATH) &&
                FileUtils.isFileReadable(MIN_PATH);
    }

    public static int getMaxIntensity() {
        try {
            return Integer.parseInt(FileUtils.readOneLine(MAX_PATH));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return -1;
    }

    public static int getMinIntensity() {
        try {
            return Integer.parseInt(FileUtils.readOneLine(MIN_PATH));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return -1;
    }

    public static int getWarningThreshold() {
        return -1;
    }

    public static int getCurIntensity() {
        try {
            return Integer.parseInt(FileUtils.readOneLine(LEVEL_PATH));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return -1;
    }

    public static int getDefaultIntensity() {
        try {
            return Integer.parseInt(FileUtils.readOneLine(DEFAULT_PATH));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return -1;
    }

    public static boolean setIntensity(int intensity) {
        return FileUtils.writeLine(LEVEL_PATH, String.valueOf(intensity));
    }
}
