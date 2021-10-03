package com.icodeu.bakeryapp.utils.flowBinding

import android.os.Looper

//source : https://github.com/ReactiveCircus/FlowBinding/blob/main/flowbinding-common/src/main/java/reactivecircus/flowbinding/common/CheckMainThread.kt

fun checkMainThread() {
    check(Looper.myLooper() == Looper.getMainLooper()) {
        "Expected to be called on the main thread but was " + Thread.currentThread().name
    }
}