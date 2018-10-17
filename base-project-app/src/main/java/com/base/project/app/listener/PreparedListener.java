package com.base.project.app.listener;

import com.base.project.commcon.context.SpringApplicationContext;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

public class PreparedListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {

        SpringApplicationContext.setAppliction(applicationPreparedEvent.getApplicationContext());
    }
}
