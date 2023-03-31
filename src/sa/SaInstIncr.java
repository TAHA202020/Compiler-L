package sa;

public class SaInstIncr implements SaInst{
    private SaVar var;
    private SaExp exp;

    public SaInstIncr(SaVar var, SaExp exp){
        this.var = var;
        this.exp = exp;
    }
    public SaVar getVar(){return this.var;}
    public SaExp getExp(){return this.exp;}

    public <T> T accept(SaVisitor <T> visitor) throws Exception{
        return visitor.visit(this);
    }

}
