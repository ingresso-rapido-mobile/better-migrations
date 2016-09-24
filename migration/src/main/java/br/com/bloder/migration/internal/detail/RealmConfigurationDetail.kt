package br.com.bloder.migration.internal.detail

import java.util.*

/**
 * Created by bloder on 24/09/16.
 */
class RealmConfigurationDetail {

    var name : String = "default.realm"
    var migration : MutableList<MigrationDetail> = ArrayList()
}