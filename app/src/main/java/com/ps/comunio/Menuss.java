package com.ps.comunio;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Menuss extends AppCompatActivity {
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Intent logout = new Intent(this, MainActivity.class);
        String usuario = ("Perfil de " + getGlobalUsuario());
        setTitle(usuario);
        appbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(appbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Fragment fragment = new fragmentInicio();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        navView = (NavigationView) findViewById(R.id.navview);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;


                        switch (menuItem.getItemId()) {
                            case R.id.Inicio:
                                fragment = new fragmentInicio();
                                fragmentTransaction = true;
                                break;
                            case R.id.reglas:
                                fragment = new FragmentoReglas();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_listJugadores:
                                fragment = new Fragment1();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_miEquipo:
                                fragment = new miEquipo();
                                fragmentTransaction = true;
                                break;
                            case R.id.menu_equipo:
                                fragment = new FragmentoEquipo();
                                fragmentTransaction = true;
                                break;
                            case R.id.logout:
                                startActivity(logout);
                                break;
                            default:
                                fragmentTransaction = false;
                                break;
                        }

                        if (fragmentTransaction) {

                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            if (menuItem.getItemId() != R.id.Inicio) {
                                getSupportActionBar().setTitle(menuItem.getTitle());
                            } else {
                                String usuario = ("Perfil de " + getGlobalUsuario());
                                getSupportActionBar().setTitle(usuario);
                            }
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getGlobalUsuario() {
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        return globalVariable.getUsuario();
    }
}