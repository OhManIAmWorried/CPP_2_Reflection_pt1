package TestPackage;

/**
 * Created by Oly on 05.03.2017.
 */
public class TestSuperClass {
    private int val = 1;
    public TestSuperClass() {}
    public TestSuperClass(int that) {val = that;}
    public int getVal() {return val;}
    public void setVal(int that) {val = that;}
    public void saySmth() {System.out.println("TestSuperClass");}
    public void beDangerous(String[] that) {for (int i = 0; i < that.length; i++) System.out.print(that[i] + " "); System.out.println("should better avoid me. Grrrr");}
}
