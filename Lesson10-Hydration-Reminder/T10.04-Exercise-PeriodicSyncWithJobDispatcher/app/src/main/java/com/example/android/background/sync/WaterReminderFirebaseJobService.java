/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.background.sync;

import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class WaterReminderFirebaseJobService extends JobService {
    private AsyncTask<JobParameters, Void, Void> mBackgroundTask = new AsyncTask<JobParameters, Void, Void>() {
        private JobParameters jobParameters;

        @Override
        protected Void doInBackground(JobParameters... params) {
            if (params.length > 0) {
                jobParameters = params[0];
            }

            ReminderTasks.executeTask(WaterReminderFirebaseJobService.this, ReminderTasks.ACTION_REMIND_BECAUSE_CHARGING);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            jobFinished(jobParameters, false);
            super.onPostExecute(aVoid);
        }
    };

    @Override
    public boolean onStartJob(JobParameters job) {
        mBackgroundTask.execute(job);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (!mBackgroundTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            mBackgroundTask.cancel(true);
        }

        return true;
    }
}
