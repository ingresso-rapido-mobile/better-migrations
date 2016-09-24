package br.com.bloder.migration.internal

/**
 * Created by bloder on 24/09/16.
 */
interface InternalMigration {

    fun up()
    fun down()
    fun change()
}