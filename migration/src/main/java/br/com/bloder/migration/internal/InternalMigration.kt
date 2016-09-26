package br.com.bloder.migration.internal

import io.realm.DynamicRealm

/**
 * Better Migrations actions
 */
interface InternalMigration {

    fun up(realm: DynamicRealm?)
    fun down(realm: DynamicRealm?)
}