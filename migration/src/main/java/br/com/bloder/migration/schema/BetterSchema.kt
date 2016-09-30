package br.com.bloder.migration.schema

import br.com.bloder.migration.schema.SCHEMA_ACTIONS.*
import io.realm.DynamicRealm
import io.realm.RealmObjectSchema
import io.realm.RealmSchema


/**
 * Created by bloder on 28/09/16.
 */

class BetterSchema {

    private var schema: RealmSchema? = null

    fun getSchema(dynamicRealm: DynamicRealm) : RealmSchema {
        this.schema = dynamicRealm.schema
        return schema as RealmSchema
    }

    fun addField(name: String, type: Class<*>) : SchemaDetails = SchemaDetails(schema as RealmSchema, name, type, ADD)
    fun removeField(name: String) : SchemaDetails = SchemaDetails(schema as RealmSchema, name, REMOVE)

    class SchemaDetails(private var schema: RealmSchema, private val name: String, private val type: Class<*>?, private val func: SCHEMA_ACTIONS) {

        constructor(schema: RealmSchema, name: String, func: SCHEMA_ACTIONS) : this(schema, name, null, func) {}

        fun into(className: String) {
            val realmObjectSchema: RealmObjectSchema = schema.get(className)
            when(func) {
                ADD -> if (!realmObjectSchema.hasField(className)) realmObjectSchema.addField(name, type)
                REMOVE -> if (realmObjectSchema.hasField(name)) realmObjectSchema.removeField(name)
            }
        }
    }
}

