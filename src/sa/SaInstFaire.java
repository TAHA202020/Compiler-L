package sa;

public class SaInstFaire implements SaInst {
    private SaExp test;
    private SaInst faire;

    public SaInstFaire(SaExp test, SaInst faire) {
        this.test = test;
        this.faire = faire;
    }

    public SaExp getTest() {
        return this.test;
    }

    public SaInst getFaire() {
        return this.faire;
    }

    public <T> T accept(SaVisitor<T> visitor) throws Exception {
        return visitor.visit(this);
    }
}
