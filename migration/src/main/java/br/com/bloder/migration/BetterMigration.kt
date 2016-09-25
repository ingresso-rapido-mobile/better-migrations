package br.com.bloder.migration

import br.com.bloder.migration.internal.detail.MigrationDetail
import io.realm.DynamicRealm
import io.realm.RealmMigration

/**
 * Abstract better migration actions to transform in
 * a realm migration
 */
class BetterMigration(private val migration: MigrationDetail) : RealmMigration {

    /**
     * Realm Migration main function
     */
    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        if (newVersion < oldVersion) {
            migration.migration?.down()
        }
        if (newVersion == migration.version) {
            migration.migration?.up()
        }
    }
}