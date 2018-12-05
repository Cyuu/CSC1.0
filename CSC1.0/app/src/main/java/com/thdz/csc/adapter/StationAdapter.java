package com.thdz.csc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thdz.csc.R;
import com.thdz.csc.bean.StationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 站点列表适配器
 */
public class StationAdapter extends BaseAdapter {

    private List<StationBean> dataList = null;
    private Context mContext;

    public StationAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public StationAdapter(Context mContext, List<StationBean> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    public List<StationBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<StationBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return null == dataList ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_stn, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final StationBean bean = dataList.get(position);
        holder.tv_station_name.setText(bean.getStnName());
        holder.tv_station_addr.setText("河北省保定市恒滨路9" + position + "号");
        holder.tv_station_area.setText(bean.getDeviceCount());
        holder.tv_station_state.setText("尚无告警");
        holder.tv_station_state.setTextColor(mContext.getResources().getColor(R.color.green_deep_color));

        if (position == 1 || position == 2) {
            holder.tv_station_state.setText("环境温度告警");
            holder.tv_station_state.setTextColor(mContext.getResources().getColor(R.color.red_color));
        }

        String value = bean.getStnState();
        if (value.equals("true")) {
            holder.running_state.setText(mContext.getResources().getString(R.string.open_true));
            holder.running_state.setTextColor(mContext.getResources().getColor(R.color.green_deep_color));
        } else {
            holder.running_state.setText(mContext.getResources().getString(R.string.open_false));
            holder.running_state.setTextColor(mContext.getResources().getColor(R.color.red_color));
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_station_name)
        TextView tv_station_name; // 站点名称
        @BindView(R.id.tv_station_addr)
        TextView tv_station_addr; // 站点地址
        @BindView(R.id.tv_station_area)
        TextView tv_station_area; // 站点区域
        @BindView(R.id.tv_station_state)
        TextView tv_station_state; // 站点区域

        @BindView(R.id.running_state)
        TextView running_state; // 状态

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
