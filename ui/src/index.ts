import { definePlugin } from "@halo-dev/console-shared";
import RedirectView from "./views/RedirectView.vue";
import { IconPlug } from "@halo-dev/components";
import { markRaw } from "vue";

export default definePlugin({
  name: "PluginNotifyMe",
  components: {},
  routes: [
    {
      parentName: "Root",
      route: {
        path: "/notifyMe",
        name: "通知我",
        component: RedirectView,
        meta: {
          title: "通知我",
          searchable: true,
          menu: {
            name: "通知我",
            group: "tool",
            icon: markRaw(IconPlug),
            priority: 0,
          },
        },
      },
    },
  ],
  extensionPoints: {},
});
