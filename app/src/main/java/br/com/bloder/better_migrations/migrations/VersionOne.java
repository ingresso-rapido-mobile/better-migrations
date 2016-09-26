package br.com.bloder.better_migrations.migrations;

import br.com.bloder.migration.Migration;
import io.realm.DynamicRealm;

/**
 * Created by bloder on 25/09/16.
 */
public class VersionOne extends Migration {

  @Override
  public void up(DynamicRealm realm) {
    realm.getSchema().get("User").addField("age", Integer.class);
  }

  @Override
  public void down(DynamicRealm realm) {
    realm.getSchema().get("User").removeField("age");
  }
}
