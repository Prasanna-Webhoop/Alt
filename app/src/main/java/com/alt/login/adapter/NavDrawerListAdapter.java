package com.alt.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.alt.R;
import com.alt.login.model.NavDrawerItem;
import com.alt.others.OnBoardingTextView;
import com.alt.utils.Utils;

import java.util.ArrayList;

public class NavDrawerListAdapter extends BaseAdapter {

    private ArrayList<NavDrawerItem> arrayListNavDrawerItems;

    private Context context;
    private LayoutInflater layoutInflater;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> arrayListNavDrawerItems) {
        this.context = context;
        this.arrayListNavDrawerItems = arrayListNavDrawerItems;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayListNavDrawerItems.size();
    }

    @Override
    public NavDrawerItem getItem(int position) {
        return arrayListNavDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Utils.out("TITLE : " + arrayListNavDrawerItems.get(position).getNavigationName());
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_navigation_drawer, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((NavDrawerItem) getItem(position), (ViewHolder) convertView.getTag(), convertView, position);
        return convertView;
    }

    private void initializeViews(NavDrawerItem navDrawerItem, ViewHolder viewHolder, View convertView, int intCellPosition) {
        //TODO implement


        if (navDrawerItem.isSelected()) {
            // Selected.
            convertView.setBackgroundColor(context.getResources().getColor(R.color.color_white));
            viewHolder.textViewNavName.setTextColor(context.getResources().getColor(R.color.color_black));
        } else {
            // UnSelected.
            convertView.setBackgroundColor(context.getResources().getColor(R.color.color_black));
            viewHolder.textViewNavName.setTextColor(context.getResources().getColor(R.color.color_white));
        }
        viewHolder.textViewNavName.setText(navDrawerItem.getNavigationName());
       /* if (intCellPosition == 1) {
            viewHolder.textViewNavName.setTextColor(context.getResources().getColor(R.color.color_black));
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colorContinueBorder));
        }*/
    }

    private class ViewHolder {
        private OnBoardingTextView textViewNavName;
        private RelativeLayout relativeLayoutRow;

        ViewHolder(View view) {
            textViewNavName = (OnBoardingTextView) view.findViewById(R.id.textView_nav_name);
            relativeLayoutRow = (RelativeLayout) view.findViewById(R.id.relativeLayout_row);
        }
    }
}