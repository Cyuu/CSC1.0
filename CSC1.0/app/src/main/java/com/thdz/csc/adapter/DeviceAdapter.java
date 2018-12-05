package com.thdz.csc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thdz.csc.R;
import com.thdz.csc.bean.DeviceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备列表适配器
 */
public class DeviceAdapter extends BaseAdapter {

    private List<DeviceBean> dataList = null;
    private Context mContext;

    public DeviceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public DeviceAdapter(Context mContext, List<DeviceBean> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    public List<DeviceBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DeviceBean> dataList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_device, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DeviceBean bean = dataList.get(position);
        holder.device_name.setText(bean.getDeviceName());
        holder.device_addr.setText(bean.getDeviceAddr());
        holder.device_state.setText(bean.getStateName());
        holder.device_desc.setText(bean.getStationName());

        int value = bean.getStateId();
        if (value == 0) {
            holder.device_state.setTextColor(mContext.getResources().getColor(R.color.green_color));
        } else {
            holder.device_state.setTextColor(mContext.getResources().getColor(R.color.red_color));
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.device_name)
        TextView device_name; // 设备名称
        @BindView(R.id.device_addr)
        TextView device_addr; // 设备地址

        @BindView(R.id.device_state)
        TextView device_state; // 设备状态
        @BindView(R.id.device_desc)
        TextView device_desc; // 设备备注

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
