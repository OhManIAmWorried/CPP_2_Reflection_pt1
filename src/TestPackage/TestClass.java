package TestPackage;

/**
 * Created by Oly on 05.03.2017.
 */
public class TestClass extends TestSuperClass implements TestInterface {
    public String testField;
    private static int value = 1;
    private int[] array;
    public String[][] arr;
    public TestClass() {testField = "Default";}
    public TestClass(String that) {testField = that;}
    public TestClass(String that, int i) {testField = that + i;}
    public void saySmth() {System.out.println("TestClass");}
    private void tellThePassword() {System.out.println("password");}
    @Override
    public void tellAboutYourself() {System.out.println("I am a TestClass");}
    public void takeThat(String[][] that) {System.out.println("that is taken");}
    public String[][] gimmeThat(String[][] that) {System.out.println("here you are"); return that;}
}
