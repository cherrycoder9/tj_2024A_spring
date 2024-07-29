package example.day10;

public class GenericPrinter< T extends Material > {
    // T : plastic , powder 일수도 있고 그외 일수도 있고
    private T material;
    public void setMaterial( T material) {
        this.material = material;
    }
    public T getMaterial() {
        return material;
    }
    public String toString(){
        return material.toString();
    }
    public void doPrint(){
        material.doPrinting(); // T : material 필드가 plastic , powder 의 메소드를 사용할수 없다.
        //  < T extends 상위클래스 > : 상위클래스의 메소드는 사용할수 있다.
    }
}
