package br.com.bloder.better_migrations.realm.internal;

import android.content.Context;

import br.com.bloder.better_migrations.migrations.VersionOne;
import br.com.bloder.migration.configuration.BetterRealmConfiguration;
import io.realm.RealmConfiguration;

/**
 * Created by bloder on 25/09/16.
 */
public class RealmConfig {

  private Context context;

  public RealmConfig(Context context) {
    this.context = context;
  }

  public RealmConfiguration configRealm() {
     return new BetterRealmConfiguration
            .Builder(context)
            .name("test.realm")
            .version(1)
            .migration(new VersionOne()).withVersion(1)
            .build();
  }
}
