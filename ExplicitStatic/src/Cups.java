/**
 * Created by liutao on 12/23/15.
 */
public class Cups {

    static Cup cup1;
    static Cup cup2;
    static Cup cup3 = new Cup(3);
    static {
        System.out.println("static block");
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }
    Cups() {
        System.out.println("Cups()");
    }
}
