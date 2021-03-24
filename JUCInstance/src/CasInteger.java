import java.util.concurrent.DelayQueue;

public class CasInteger {
    static volatile int anInt=0;
    static volatile int version=0;

    static int getInt(){
        return anInt;
    }
    static int getVersion(){
        return version;
    }
    static boolean inInt(int version1){
        if (version1==version){
            anInt+=1;
            return true;
        }
        return false;
    }

    static void inInt(){
        int version = getVersion();
        boolean res=false;
        while (!res){
            res=inInt(version);

        }
    }

}
