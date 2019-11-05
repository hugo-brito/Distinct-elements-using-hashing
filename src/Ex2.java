import java.util.Scanner;
import java.util.InputMismatchException;
class Ex2  {

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
        System.out.println(rho(Ex1.h(in.nextInt())));
    }
    in.close();
  }

  public static int rho(int x) {
    if (x == 0) {
      throw new InputMismatchException("Zero is undefined");
    }
    return Integer.numberOfLeadingZeros(x);
  }
}
