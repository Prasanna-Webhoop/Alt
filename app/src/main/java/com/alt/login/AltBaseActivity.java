package com.alt.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alt.R;
import com.alt.login.adapter.NavDrawerListAdapter;
import com.alt.login.model.NavDrawerItem;
import com.alt.others.OnBoardingTextView;
import com.alt.utils.Utils;

import java.util.ArrayList;

public class AltBaseActivity extends AppCompatActivity {

    // Main Components.
    private Intent intent = null;
    private ListView listViewDrawer;
    public static DrawerLayout drawerLayout;
    private ArrayList<NavDrawerItem> arrayListNavItems = new ArrayList<NavDrawerItem>();
    private OnBoardingTextView textViewTitle, textViewTitleRight, textViewMenu, textViewUserName;
    private RelativeLayout objRelativeBase;
    private FrameLayout objFrameLayoutContainer;

    // Drawer components.
    // ListView components.
    private String[] navItems;
    private Activity activity;
    private NavDrawerListAdapter navDrawerListAdapter;
    private Button buttonLogOut;
    private LinearLayout linearLayoutProfile;
    private ImageView imageViewProfile;
    /* // Preferences.
     private UserPreference userPreference;
     private CompanyInfoModel companyInfoModel;
     private UserInfoModel userInfoModel;
 */
    // TOOL BAR COMPONENTS.
    RelativeLayout objToolbar;
    TextView objTextViewToolbarTitle, objTextViewToolbarRightTitle;
    private ImageView objImageViewBackArrow;
    // Header view Components goes here.
    private OnBackPressedListener onBackPressedListener;


    public OnBackPressedListener getOnBackPressedListener() {
        return onBackPressedListener;
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    // Swipe gesture.
    private GestureDetectorCompat gestureDetectorCompat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_main_baord);
        activity = AltBaseActivity.this;
        initView();
        // Header view Components goes here.
        // Init Drawer components.
        initViewDrawer();
        //Init Data for drawer.
        setDrawerItems();
        // Design the layout according to the web color response which will be fetch from the preferences.
        setDataToComponents();
        // Main Listeners.
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Main Listeners.
//        initView();
        // Init Drawer components.
//        initViewDrawer();
        // Main Listeners.
//        setListener();
//        setDataToComponents();
    }

    public void invalidateDrawerItem() {
        setDataToComponents();
    }

    /**
     * Design the layout according to the web response
     * i.e. color, text, etc.
     */
    private void setDataToComponents() {
        /*if (userInfoModel != null && companyInfoModel != null) {
            textViewMenu.setTextColor(Color.parseColor(companyInfoModel.getColor()));
            textViewTitle.setTextColor(Color.parseColor(companyInfoModel.getColor()));
            //Drawer components.
            Utils.out("USER NAME IN DRAWER : " + userInfoModel.getFirst_name());
            String[] name = userInfoModel.getFirst_name().split(" ");
            userInfoModel = userPreference.getUserInfoModel();
            if (!userInfoModel.getFirst_name().isEmpty()) {
                textViewUserName.setText(Html.fromHtml("<b>" + name[0] + "</b>"));
                if (!userInfoModel.getPhoto().isEmpty())
                    Picasso.with(activity).load(Constants.BASE + userInfoModel.getPhoto()).resize(80, 80).transform(new CircleTransform()).into(imageViewProfile);
            }
        }*/
    }

    /**
     * Initialise main components.
     */
    private void initView() {
        // ToolBar Components.
        objImageViewBackArrow = (ImageView) findViewById(R.id.toolbar_imageView_back);
        textViewTitle = (OnBoardingTextView) findViewById(R.id.toolbar_title);
        textViewTitleRight = (OnBoardingTextView) findViewById(R.id.toolbar_title_right);
        textViewMenu = (OnBoardingTextView) findViewById(R.id.textView_menu);
    }

    public interface OnBackPressedListener {
        public void onBackArrowPressed();
    }


    private void setListener() {


        Utils.out("LISTENER CALLED.");
        objImageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedListener.onBackArrowPressed();
            }
        });
        //Drawer components listeners.
        // Profile Image Components Listeners.
        linearLayoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawerLayout();
               /* intent = new Intent(activity, MainBoardProfileActivity.class);
                startActivity(intent);
                finish();*/
            }
        });
        listViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                if (position != 1) {
                    closeDrawerLayout();
                    listViewDrawer.setItemChecked(position, true);
                    setSelectedPosition(position);
                }
                // Added handler to close drawer smoothly and perform next action.
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // Handle click and push to next.
                        NavDrawerItem navDrawerItem = arrayListNavItems.get(position);
                        Intent intent = null;
                        switch (navDrawerItem.getNavigationName()) {
                            case "Onboarding":
                                break;
                            case "Expenses":
                                break;

                        }

                        if (intent != null) {
                            startActivity(intent);
                            finish();
                        }
                    }
                }, 300);
            }
        });

        // Button Logout Listener.
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 08/12/16 implements logout app.

                closeDrawerLayout();
               /* Intent intent = new Intent(com.althr.onboarding.main_board.AltBaseActivity.this, LaunchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Utils.pushToNext(activity, intent);
                finishAffinity();*/
            }
        });

        // Tool Bar Listeners.
        textViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.out("MENU CLICKED.");
                openDrawerLayout();
            }
        });
    }

    /**
     * This will set the selected position in DrawerListView.
     */
    public void setSelectedPosition(int position) {
        // Set The Unselected background for others and selected for the current position.
        for (int i = 0; i < navItems.length; i++) {
            arrayListNavItems.get(i).setSelected(false);
        }
        // Set The selected background.
        arrayListNavItems.get(position).setSelected(true);
        navDrawerListAdapter.notifyDataSetChanged();
    }

    /**
     * Set Data to drawer listView, which will refer the data from the strings.xml file with array of named as
     * "nav_items".
     */
    private void setDrawerItems() {
        Utils.out("SET DRAWER ITEMS CALLED.");
        navDrawerListAdapter = new NavDrawerListAdapter(activity, arrayListNavItems);
        navItems = getResources().getStringArray(R.array.alt_home_drawer_items);
        for (int i = 0; i < navItems.length; i++) {
            NavDrawerItem navDrawerItem = new NavDrawerItem(navItems[i], false);
            arrayListNavItems.add(navDrawerItem);
            Utils.out("NAV ITEM NAME :" + navDrawerItem.getNavigationName());
        }
        listViewDrawer.setAdapter(navDrawerListAdapter);
        navDrawerListAdapter.notifyDataSetChanged();
    }

    public void setAddedDrawerItems() {
        Utils.out("SET DRAWER ITEMS CALLED.");
        arrayListNavItems.clear();
//        navItems = getResources().getStringArray(R.array.nav_checklist_add);
        navItems = getResources().getStringArray(R.array.nav_items);
        for (int i = 0; i < navItems.length; i++) {
            NavDrawerItem navDrawerItem = new NavDrawerItem(navItems[i], false);
            arrayListNavItems.add(navDrawerItem);
        }
        navDrawerListAdapter = new NavDrawerListAdapter(activity, arrayListNavItems);
        listViewDrawer.setAdapter(navDrawerListAdapter);
    }

    /**
     * Initialise drawer components.
     */
    private void initViewDrawer() {
        // Profile image components.
        linearLayoutProfile = (LinearLayout) findViewById(R.id.linear_layout_profile);
        imageViewProfile = (ImageView) findViewById(R.id.imageView_profile);
        textViewUserName = (OnBoardingTextView) findViewById(R.id.textView_username);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listViewDrawer = (ListView) findViewById(R.id.listView_drawer);

        // Drawer Layout Bottom components.
        buttonLogOut = (Button) findViewById(R.id.button_log_out);
    }

    /**
     * To open drawer.
     */
    protected void openDrawerLayout() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    /**
     * To close drawer.
     */
    protected void closeDrawerLayout() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void setTitle(String strTitle) {
        textViewTitle.setText(Html.fromHtml("<b>" + strTitle + "</b>"));
    }


    /**
     * TO Hide and show back arrow imageView.
     *
     * @param show <b>true</b> show back arrow imageView <b>false</b> otherwise.
     */
    public void showBackArrow(boolean show) {
        if (show) {
            textViewMenu.setVisibility(View.GONE);
            objImageViewBackArrow.setVisibility(LinearLayout.VISIBLE);
        } else {
            textViewMenu.setVisibility(View.VISIBLE);
            objImageViewBackArrow.setVisibility(LinearLayout.GONE);
        }
    }

    @Override
    public void onBackPressed() {
//        Utils.pushToBack(activity);
        onBackPressedListener.onBackArrowPressed();

    }

    // BACK ARROW LISTENER METHOD.

//    public void setOnBackArrowPressed(OnBackArrowPressed onBackArrowPressed) {
//        this.onBackArrowPressed = onBackArrowPressed;
//    }

    public void setDrawerLayoutOpenClosed(boolean result) {
        if (result) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }


}
