package csee.ce291_team02.MugBrowser;

import com.codebrig.journey.proxy.CefBrowserProxy;

public class BrowserHelper {
    public static class Loader{
        public static CefBrowserProxy browserProxy;

        public static void LoadUrl(String Url){
            browserProxy.loadURL(Url);
        }
    }
}
