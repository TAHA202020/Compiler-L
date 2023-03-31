package sa;
import util.Type;

public class SaExpCarre implements SaExp{
    private SaExp op1;

    public SaExpCarre(SaExp op1){
        this.op1 = op1;
    }

    public SaExp getOp1(){return this.op1;}
    public SaExp getOp2(){return null;}

    public Type getType(){
        return Type.ENTIER;
    }

    public <T> T accept(SaVisitor <T> visitor) throws Exception{
        return visitor.visit(this);
    }
}
