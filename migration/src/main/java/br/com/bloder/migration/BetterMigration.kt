package br.com.bloder.migration

import br.com.bloder.migration.internal.InternalMigration
import io.realm.DynamicRealm
import io.realm.RealmMigration

/**
 * Created by bloder on 24/09/16.
 */
class BetterMigration(internalMigration: InternalMigration) : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {

    }
}