package recipe.android.translationeditorapp

import android.content.Context

/**
 * Created by lorence on 12/11/2018.
 *
 */

class Application : android.app.Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocalHelper.onAttach(base, "en"))
    }
}