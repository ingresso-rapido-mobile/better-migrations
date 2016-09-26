package br.com.bloder.migration

import br.com.bloder.migration.internal.InternalMigration
import io.realm.DynamicRealm

/**
 * Class to abstract realm migration
 */
open class Migration : InternalMigration {

    /**
     * Represents what will change in a migration version
     */
    override fun up(realm: DynamicRealm?) {}

    /**
     * Represents what will be discarded when that migration version
     * does not exist anymore
     */
    override fun down(realm: DynamicRealm?) {}
}