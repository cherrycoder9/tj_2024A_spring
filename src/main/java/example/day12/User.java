package example.day12;

public class User {

    String name;
    int age;

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if( obj instanceof User ){
            User user = (User)obj;
            if( this.name.equals( user.name ) ){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
