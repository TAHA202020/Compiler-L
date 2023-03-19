package sa;
import util.Type;
import util.Error;
import ts.*;

public class SaTypeCheck extends SaDepthFirstVisitor <Void>{
    private TsItemFct fonctionCourante;

    public SaTypeCheck(SaNode root)
    {
        try{
            root.accept(this);
        }
        catch(ErrorException e){
            System.err.print("ERREUR DE TYPAGE : ");
            System.err.println(e.getMessage());
            System.exit(e.getCode());
        }
        catch(Exception e){}
    }

    public void defaultIn(SaNode node)
    {
        //			System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(SaNode node)
    {
        //		System.out.println("</" + node.getClass().getSimpleName() + ">");
    }
    Type type;
    public Void visit(SaExpAdd node) throws Exception {
        node.getOp1().accept(this);
        Type op1=type;
        node.getOp2().accept(this);
        Type op2=type;
        if(!(op1.nom().equals(op2.nom()) && op1.nom().equals("entier")))
        {
            throw new ErrorException(Error.TYPE,"les deux operants doivent etre entier");
        }
        return null;
    }
    public Void visit(SaExpInt node)
    {
        type=Type.ENTIER;
        return null;
    }
    public Void visit(SaInstEcriture node) throws Exception {
        node.getArg().accept(this);
        return null;
    }
    public Void visit(SaExpFaux node) throws ErrorException
    {
        type=Type.BOOL;
        return null;
    }
    public Void visit(SaExpVrai node) throws ErrorException
    {
        type=Type.BOOL;
        return null;
    }
    public Void visit(SaExpNot node) throws Exception
    {
        node.getOp1().accept(this);
        if (!type.nom().equals("bool"))
        {
            throw new ErrorException(Error.TYPE,"Must be bool");
        }
        type=Type.BOOL;
        return null;
    }
    public Void visit(SaInstAffect node)throws Exception
    {
        node.getLhs().accept(this);
        Type op1=type;
        node.getRhs().accept(this);
        Type op2=type;
        System.out.println(op1.nom());
        if (!(op1.nom().equals(op2.nom())))
            throw new ErrorException(Error.TYPE,"cannot affect to a differrent type");
        return null;
    }
    public Void visit(SaVarSimple node)
    {
        type=node.getTsItem().getType();
        return null;
    }
}
