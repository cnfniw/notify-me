package org.xiqi.notifyme;

import run.halo.app.plugin.PluginContext;
import org.springframework.stereotype.Component;
import run.halo.app.plugin.BasePlugin;

@Component
public class NotifyMePlugin extends BasePlugin {
    public NotifyMePlugin(PluginContext pluginContext) {
        super(pluginContext);
    }

    @Override
    public void start() {
        System.out.println("插件启动成功！");
    }

    @Override
    public void stop() {
        System.out.println("插件停止！");
    }
}
