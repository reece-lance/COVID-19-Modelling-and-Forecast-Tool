package csee.ce291_team02;

public class MugLogger {
    public static boolean isEnabled(){
        return false;
    }

    public static void log(Object o){
        if(MugLogger.isEnabled()) {
            System.out.println(o.toString());
        }
    }
}
