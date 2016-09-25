package br.com.bloder.migration

import br.com.bloder.migration.internal.InternalMigration

/**
 * Class to abstract realm migration
 */
class Migration : InternalMigration {

    /**
     * Represents what will change in a migration version
     */
    override fun up() {}

    /**
     * Represents what will be discarded when that migration version
     * does not exist anymore
     */
    override fun down() {}
}