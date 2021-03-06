package br.com.bloder.migration.schema

import br.com.bloder.migration.schema.SCHEMA_ACTIONS.*
import io.realm.DynamicRealm
import io.realm.RealmObjectSchema
import io.realm.RealmSchema
import java.util.*


/**
 * Created by bloder on 28/09/16.
 */

class BetterSchema {

    private var schema: RealmSchema? = null

    fun setSchema(dynamicRealm: DynamicRealm) {
        this.schema = dynamicRealm.schema
    }
    
    fun addField(name: String, type: Class<*>) : SchemaDetails = SchemaDetails(schema as RealmSchema, name, type, null, ADD)
    fun removeField(name: String) : SchemaDetails = SchemaDetails(schema as RealmSchema, name, REMOVE)
    fun changeFieldType(name: String, oldType: Class<*>, newType: Class<*>) : SchemaDetails = SchemaDetails(schema as RealmSchema, name, newType, oldType, CHANGE_TYPE)

    class SchemaDetails(private var schema: RealmSchema, private val name: String, private val type: Class<*>?, private val oldType: Class<*>?, private val func: SCHEMA_ACTIONS) {

        constructor(schema: RealmSchema, name: String, func: SCHEMA_ACTIONS) : this(schema, name, null, null, func) {}

        fun inClass(className: String) {
            val realmObjectSchema: RealmObjectSchema = schema.get(className)
            when(func) {
                ADD -> if (!realmObjectSchema.hasField(name)) realmObjectSchema.addField(name, type)
                REMOVE -> if (realmObjectSchema.hasField(name)) realmObjectSchema.removeField(name)
                CHANGE_TYPE -> if (realmObjectSchema.hasField(name)) changeType(realmObjectSchema, name, type as Class<*>)
            }
        }

        private fun changeType(realmObjectSchema: RealmObjectSchema, name: String, newType: Class<*>) {
            val randomExtension: String = name + UUID.randomUUID().toString()
            realmObjectSchema.addField(randomExtension, newType)
            realmObjectSchema.transform(RealmObjectSchema.Function { obj -> obj.set(randomExtension, obj.get(name))})
            realmObjectSchema.removeField(name)
            realmObjectSchema.renameField(randomExtension, name)
        }
    }
}
