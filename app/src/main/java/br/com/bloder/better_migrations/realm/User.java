package br.com.bloder.better_migrations.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bloder on 25/09/16.
 */
public class User extends RealmObject {

  @PrimaryKey
  private long userId;

  private String name;
}
