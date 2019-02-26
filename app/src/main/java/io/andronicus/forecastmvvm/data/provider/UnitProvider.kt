package io.andronicus.forecastmvvm.data.provider

import io.andronicus.forecastmvvm.internal.UnitSystem

/**
 * Created by Andronicus on 2/26/2019.
 */
interface UnitProvider {

    fun getUnitSystem() : UnitSystem
}