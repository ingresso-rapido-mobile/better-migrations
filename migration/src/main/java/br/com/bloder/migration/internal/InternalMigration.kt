package br.com.bloder.migration.internal

/**
 * Better Migrations actions
 */
interface InternalMigration {

    fun up()
    fun down()
}