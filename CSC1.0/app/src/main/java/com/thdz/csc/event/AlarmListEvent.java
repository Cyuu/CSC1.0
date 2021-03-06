package com.thdz.csc.event;


import com.thdz.csc.bean.PushBeanBase;

import java.io.Serializable;

/**
 * 最新告警列表发生变化，
 */
public class AlarmListEvent implements Serializable{

    private PushBeanBase pushBean;

    public AlarmListEvent(){}

    public AlarmListEvent(PushBeanBase pushBean) {
        this.pushBean = pushBean;
    }

    public PushBeanBase getPushBean() {
        return pushBean;
    }

    public void setPushBean(PushBeanBase pushBean) {
        this.pushBean = pushBean;
    }

    @Override
    public String toString() {
        return "AlarmListEvent{" +
                "pushBean=" + pushBean +
                '}';
    }
}
