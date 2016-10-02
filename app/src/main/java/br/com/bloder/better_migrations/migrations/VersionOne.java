package br.com.bloder.better_migrations.migrations;

import br.com.bloder.migration.Migration;
import br.com.bloder.migration.schema.BetterSchema;
import io.realm.DynamicRealm;

/**
 * Created by bloder on 25/09/16.
 */
public class VersionOne extends Migration {

  @Override
  public void up(DynamicRealm realm) {
    BetterSchema betterSchema = new BetterSchema();
    betterSchema.setSchema(realm);
    betterSchema.addField("age", Integer.class).inClass("User");
  }

  @Override
  public void down(DynamicRealm realm) {
    BetterSchema betterSchema = new BetterSchema();
    betterSchema.setSchema(realm);
    betterSchema.removeField("age").inClass("User");
  }
}
