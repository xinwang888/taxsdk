/*
 * Copyright 2015 Yan Zhenjie
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
package com.geshui51.www;

import com.igeshui.sdk.tax.MsgCallback;
import com.igeshui.sdk.tax.TaxLib;

/**
 * Created in Oct 23, 2015 12:59:13 PM.
 *
 * @author Yan Zhenjie.
 */
public class Application extends android.app.Application {

    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        //1初始化sdk
        TaxLib.getInstance().init(this, new MsgCallback() {
            @Override
            public void loginSuccess(String msg) {

            }

            @Override
            public void failed(String msg) {

            }
        });

    }

    public static Application getInstance() {
        return mInstance;
    }

}
