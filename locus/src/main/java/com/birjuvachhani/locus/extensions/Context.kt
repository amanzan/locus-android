package com.birjuvachhani.locus.extensions

import android.content.Context
import com.birjuvachhani.locus.AvailableService
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

/**
 * Checks if the google play service is available.
 *
 * @receiver an instance of [Context].
 *
 * @return an instance of [Boolean].
 */
internal fun Context.isGSMAvailable(): Boolean {
    return try {
        GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS
    } catch (e: Exception) {
        false
    }
}


/**
 * Checks if the huawei mobile service is available.
 *
 * @receiver an instance of [Context].
 *
 * @return an instance of [Boolean].
 */
internal fun Context.isHSMAvailable(): Boolean {
    return try {
        HuaweiApiAvailability.getInstance()
            .isHuaweiMobileServicesAvailable(this) == com.huawei.hms.api.ConnectionResult.SUCCESS
    } catch (e: NoClassDefFoundError) {
        false
    }
}

/**
 * Checks if the Google play service or the Huawei mobile service is available.
 *
 * @receiver an instance of [Context].
 *
 * @return an instance of [AvailableService].
 */
fun Context.getAvailableService(): AvailableService = when {
    isGSMAvailable() -> AvailableService.GMS

    isHSMAvailable() -> AvailableService.HMS

    else -> AvailableService.NONE
}
