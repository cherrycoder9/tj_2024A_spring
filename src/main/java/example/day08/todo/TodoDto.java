package example.day08.todo;

public class TodoDto {

    private int tno;
    private String tcontent;
    private int tstate;

    public TodoDto(){}

    public TodoDto(int tno, String tcontent, int tstate) {
        this.tno = tno;
        this.tcontent = tcontent;
        this.tstate = tstate;
    }

    public int getTno() {
        return tno;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }

    public String getTcontent() {
        return tcontent;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }

    public int getTstate() {
        return tstate;
    }

    public void setTstate(int tstate) {
        this.tstate = tstate;
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "tno=" + tno +
                ", tcontent='" + tcontent + '\'' +
                ", tstate=" + tstate +
                '}';
    }
}
