/**
 * Created by liutao on 12/30/15.
 */
class GException extends Exception {
    GException(String s) { super(s); }
}

class HException extends Exception {
    HException(String s) { super(s); }
}

public class Ex10 {
    static void f() {
        try {
            try {
                g();
            } catch(GException ge) {
                System.out.println("Caught GException in f inner try");
                ge.printStackTrace(System.out);
                HException he = new HException("from f(), inner try");
                he.initCause(new NullPointerException());
                throw he;
            }
        } catch(HException he) {
            System.out.println("Caught HException in f() outer try");
            he.printStackTrace(System.out);
        }
    }
    static void g() throws GException {
        throw new GException("from g()");
    }
    public static void main(String[] args) {
        f();
    }
}
