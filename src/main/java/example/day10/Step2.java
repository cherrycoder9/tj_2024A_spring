package example.day10;

public class Step2 {
    public static void main(String[] args) {

        // 1.
        GenericPrinter<Powder> powderPrinter = new GenericPrinter<>();

        powderPrinter.setMaterial( new Powder() );
        Powder powder = powderPrinter.getMaterial();
        System.out.println( powderPrinter );

        // 2.
        GenericPrinter< Plastic > plasticPrinter = new GenericPrinter<>();

        plasticPrinter.setMaterial( new Plastic() );
        Plastic plastic = plasticPrinter.getMaterial();
        System.out.println( plasticPrinter );

    }
}
