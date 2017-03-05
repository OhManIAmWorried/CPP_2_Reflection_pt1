package TestPackage;

/**
 * Created by Oly on 05.03.2017.
 */
public interface TestInterface {
    final int secret = 42;
    public static void tellTheSecret() {System.out.println(secret);}
    public void tellAboutYourself();
}
