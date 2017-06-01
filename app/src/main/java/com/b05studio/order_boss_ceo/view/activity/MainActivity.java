package com.b05studio.order_boss_ceo.view.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.PopupWindow;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.b05studio.order_boss_ceo.R;
import com.b05studio.order_boss_ceo.view.fragment.MyRestaurantFragment;
import com.b05studio.order_boss_ceo.view.fragment.ProcessedFragment;
import com.b05studio.order_boss_ceo.view.fragment.ReservationListFragment;
import com.b05studio.order_boss_ceo.view.model.User;

public class MainActivity extends AppCompatActivity {
    private ReservationListFragment reservationListFragment;
    private ProcessedFragment processedFragment;
    private MyRestaurantFragment myRestaurantFragment;
    private Fragment currentSelectedFragment;
    public static PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO:2017.06.02 User임의생성
        User.setCurrentUser(new User("1", "김만수"));

        initBottomNaviBar();
        initFragment();
    }

    private void initFragment() {
        reservationListFragment = new ReservationListFragment();
        processedFragment = new ProcessedFragment();
        myRestaurantFragment = new MyRestaurantFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, reservationListFragment).commit();
    }

    private void initBottomNaviBar() {
        BottomNavigationBar bottomNavigationBar;
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.mainBottomNavigationBar);

        BottomNavigationItem firstBottomItem = new BottomNavigationItem(R.drawable.icon_main_checked);
        Drawable inActiveIcon1 = getResources().getDrawable(R.drawable.icon_main_unchecked);
        setTint(inActiveIcon1, Color.parseColor("#888888"));
        firstBottomItem.setInactiveIcon(inActiveIcon1);
        //firstBottomItem.setInactiveIcon(getResources().getDrawable(R.drawable.icon_main_empty));
        //firstBottomItem.setInActiveColor(Color.parseColor("#888888"));


        BottomNavigationItem secondBottomItem = new BottomNavigationItem(R.drawable.icon_order_list_checked);
        Drawable inActiveIcon2 = getResources().getDrawable(R.drawable.icon_order_list_unckecked);
        setTint(inActiveIcon2, Color.parseColor("#888888"));
        secondBottomItem.setInactiveIcon(inActiveIcon2);
        // secondBottomItem.setInActiveColor(Color.parseColor("#888888"));

        BottomNavigationItem thirdBottomItem = new BottomNavigationItem(R.drawable.icon_profile_checked);
        Drawable inActiveIcon3 = getResources().getDrawable(R.drawable.icon_profile_unchecked);
        setTint(inActiveIcon3, Color.parseColor("#888888"));
        thirdBottomItem.setInactiveIcon(inActiveIcon3);

        // thirdBottomItem.setInactiveIcon(getResources().getDrawable(R.drawable.icon_profile_unchecked));
        // thirdBottomItem.setInActiveColor(Color.parseColor("#888888"));

        // firstBottomItem.setInactiveIcon()
        bottomNavigationBar
                .addItem(firstBottomItem)
                .addItem(secondBottomItem)
                .addItem(thirdBottomItem).initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        currentSelectedFragment = reservationListFragment;
                        break;
                    case 1:
                        currentSelectedFragment = processedFragment;
                        break;
                    case 2:
                        currentSelectedFragment = myRestaurantFragment;
                        break;
                    default:
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, currentSelectedFragment).addToBackStack(null).commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }


            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void setTint(Drawable d, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(d);
        DrawableCompat.setTint(wrappedDrawable, color);
    }

    @Override
    public void onBackPressed() {
        if(popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        else
            finish();
    }
}