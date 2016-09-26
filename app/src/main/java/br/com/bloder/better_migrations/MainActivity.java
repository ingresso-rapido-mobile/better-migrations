package br.com.bloder.better_migrations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.bloder.better_migrations.realm.internal.RealmConfig;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RealmConfiguration realmConfiguration = new RealmConfig(this).configRealm();
  }
}
