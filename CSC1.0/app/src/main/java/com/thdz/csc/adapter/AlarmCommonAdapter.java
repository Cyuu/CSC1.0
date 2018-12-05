package com.thdz.csc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thdz.csc.R;
import com.thdz.csc.bean.AlarmBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 告警列表适配器
 */
public class AlarmCommonAdapter extends BaseAdapter {

    private List<AlarmBean> dataList = null;
    private Context mContext;

    public AlarmCommonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public AlarmCommonAdapter(Context mContext, List<AlarmBean> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    public List<AlarmBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<AlarmBean> dataList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_alarm, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AlarmBean bean = dataList.get(position);
        holder.alarm_name.setText(bean.getAlarmName());
        holder.station_name.setText(bean.getStationName());
        holder.device_name.setText(bean.getDeviceName());
        holder.start_time.setText(bean.getBeginTime());
        holder.stop_time.setText(bean.getEndTime());
        String alarmCode = bean.getAlarmLevel();

        if (alarmCode.equals("1")) { // 1级 告警
            holder.alarm_type.setText("巡视");
            holder.alarm_type.setBackgroundResource(R.drawable.bg_red_left_corner);
        } else if (alarmCode.equals("2")) { // 2级 告警
            holder.alarm_type.setText("预警");
            holder.alarm_type.setBackgroundResource(R.drawable.bg_orange_left_corner);
        } else if (alarmCode.equals("3")) { // 3级 告警
            holder.alarm_type.setText("小物体告警");
            holder.alarm_type.setBackgroundResource(R.drawable.bg_yellow_left_corner);
        } else { // 没有
            holder.alarm_type.setText("");
            holder.alarm_type.setBackgroundResource(R.color.transparent);
        }

        String value = bean.getOpenState();
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
        @BindView(R.id.alarm_name)
        TextView alarm_name; // 告警名称
        @BindView(R.id.station_name)
        TextView station_name; // 站点名称
        @BindView(R.id.device_name)
        TextView device_name; // 设备名称
        @BindView(R.id.running_state)
        TextView running_state; // 该站点是否开通了...
        @BindView(R.id.start_time)
        TextView start_time; // 告警开始时间
        @BindView(R.id.stop_time)
        TextView stop_time; // 取消结束时间

        @BindView(R.id.alarm_type)
        TextView alarm_type; // 告警类型名称

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
