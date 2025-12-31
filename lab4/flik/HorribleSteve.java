package flik;

public class HorribleSteve {
    public static void main(String [] args) throws Exception {
        /// Find a problem of Flik.isSameNumber.
        int i = 0;
        for (int j = 0; i < 500; i += 1, j += 1) {
            if (!Flik.isSameNumber(i, j)) {
                System.out.println("Something goes wrong with the isSameNumber.");
                throw new Exception(
                        String.format("i:%d not same as j:%d ??", i, j));
            }
        }
        System.out.println("Problem fixed");
    }
}
