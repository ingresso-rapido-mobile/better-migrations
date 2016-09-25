package br.com.bloder.migration.internal.detail

import java.util.*

/**
 * Represents a realm configuration and his properties
 */
class RealmConfigurationDetail {

    var name : String = "default.realm"
    var version : Long = 0
    var migration : MutableList<MigrationDetail> = ArrayList()
}