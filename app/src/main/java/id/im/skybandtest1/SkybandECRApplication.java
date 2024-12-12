/*
 * Copyright (c) 2012-20 GIRMITI SOFTWARE PVT. LTD. All rights reserved.
 * ----------------------------------------------------------------------------
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code distribution.
 *
 * Unless required by applicable law or agreed to in writing, this file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the specific language
 * governing permissions and limitations under the License.
 */
package id.im.skybandtest1;

import android.app.Application;

public class SkybandECRApplication extends Application {

    private static SkybandECRApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

//        if (BuildConfig.DEBUG) {
//            Stetho.initialize(
//                    Stetho.newInitializerBuilder(this)
//                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                            .build()
//            );
//        }
    }

    public static SkybandECRApplication Instance() {
        return instance;
    }

}