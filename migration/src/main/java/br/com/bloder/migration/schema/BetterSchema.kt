package br.com.bloder.migration.schema

import io.realm.DynamicRealm
import io.realm.RealmObjectSchema
import io.realm.RealmSchema


/**
 * Created by bloder on 28/09/16.
 */

class BetterSchema {

    private var schema: RealmSchema? = null
    private val add: String = "add"

    fun getSchema(dynamicRealm: DynamicRealm) : RealmSchema {
        this.schema = dynamicRealm.schema
        return schema as RealmSchema
    }

    fun addField(name: String, type: Class<*>) : SchemaDetails = SchemaDetails(schema as RealmSchema, name, type, add)

    class SchemaDetails(private var schema: RealmSchema, private val name: String, private val type: Class<*>, private val func: String) {

        fun into(className: String) {
            val realmObjectSchema: RealmObjectSchema = schema.get(className)
            when(func.toUpperCase()) {
                SCHEMA_ACTIONS.ADD.name -> if (!realmObjectSchema.hasField(className)) realmObjectSchema.addField(name, type)
            }
        }
    }
}

