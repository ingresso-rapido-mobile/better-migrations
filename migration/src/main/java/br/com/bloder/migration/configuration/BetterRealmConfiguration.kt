package br.com.bloder.migration.configuration

import android.content.Context
import br.com.bloder.migration.BetterMigration
import br.com.bloder.migration.Migration
import br.com.bloder.migration.internal.detail.MigrationDetail
import br.com.bloder.migration.internal.detail.RealmConfigurationDetail
import io.realm.RealmConfiguration

/**
 * Get some properties to create realm configuration object
 */
class BetterRealmConfiguration() {

    /**
     * Treat all realm configuration properties
     *
     * @param context build realm configuration context
     * @param realmConfigurationDetails build realm configuration properties
     */
    class Builder(private val context : Context) {

        private var realmConfigurationDetails : RealmConfigurationDetail = RealmConfigurationDetail()
        private var migrationDetail : MigrationDetail = MigrationDetail()

        constructor(context: Context, realmConfigurationDetail: RealmConfigurationDetail) : this(context) {
            this.realmConfigurationDetails = realmConfigurationDetail
        }

        /**
         * Set realm name in realm configuration details
         *
         * @param name realm file name
         * @return Builder class instance
         */
        fun name (name: String) : Builder {
            realmConfigurationDetails.name = name
            return this
        }

        /**
         * Set realm version in realm configuration details
         *
         * @param version realm configuration version
         * @return Builder class instance
         */

        fun version (version: Long) : Builder {
            this.realmConfigurationDetails.version = version
            return this
        }

        /**
         * Set a migration in migration detail
         *
         * @param migration migration class
         * @return Builder class instance
         */
        fun migration (migration : Migration) : MigrationBuilder {
            this.migrationDetail.migration = migration
            return MigrationBuilder(context, migrationDetail, realmConfigurationDetails)
        }

        /**
         * Build a realm configuration object
         *
         * @return realm configuration object instance
         */
        fun build() : RealmConfiguration = BetterRealmConfiguration(realmConfigurationDetails, context).createRealmConfiguration()
    }

    /**
     * Set each migration version
     *
     * @param context call builder class with correct context
     * @param migrationDetail receive version
     * @param realmConfigurationDetails set migration in this object
     */
    class MigrationBuilder(private val context: Context,
                           private var migrationDetail: MigrationDetail,
                           private var realmConfigurationDetails: RealmConfigurationDetail) {

        /**
         * Set migration specific version
         *
         * @param version migration version
         * @return Builder class instance
         */
        fun withVersion (version: Long) : Builder {
            migrationDetail.version = version
            realmConfigurationDetails.migration.add(migrationDetail)
            return Builder(context, realmConfigurationDetails)
        }
    }

    private var realmConfigurationDetails : RealmConfigurationDetail = RealmConfigurationDetail()
    private var context : Context? = null

    constructor(realmConfigurationDetail: RealmConfigurationDetail, context: Context) : this() {
        this.realmConfigurationDetails = realmConfigurationDetail
        this.context = context
    }

    /**
     * Create realm configuration object instance
     *
     * @return realm configuration object
     */
    fun createRealmConfiguration() : RealmConfiguration {
        val realmConfiguration : RealmConfiguration.Builder = RealmConfiguration.Builder(context)
        realmConfiguration.name(realmConfigurationDetails.name)
        realmConfiguration.schemaVersion(realmConfigurationDetails.version)
        for (migration : MigrationDetail in realmConfigurationDetails.migration) realmConfiguration.migration(BetterMigration(migration))
        return realmConfiguration.build()
    }
}