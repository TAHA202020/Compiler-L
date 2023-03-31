package sa;
import util.Type;
import util.Error;
import ts.*;

import java.util.List;

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
        System.out.println(op1.nom());
        if((op1.nom().equals(op2.nom()) && op1.nom().equals("entier"))) {
            type = Type.ENTIER;
        }else
            throw new ErrorException(Error.TYPE,"les deux operants doivent etre entier");
        return null;
    }
    public Void visit(SaExpCarre node) throws Exception
    {
        node.getOp1().accept(this);
        Type op1=type;
        if (op1.nom().equals("entier"))
        {
            type=Type.ENTIER;
        }else
        {
            throw new ErrorException(Error.TYPE,"Operants must be entier");
        }
        return  null;
    }
    public Void visit(SaExpDiv node) throws Exception {
        node.getOp1().accept(this);
        Type op1=type;
        node.getOp2().accept(this);
        Type op2=type;
        if(!(op1.nom().equals(op2.nom()) && op1.nom().equals("entier")))
        {
            throw new ErrorException(Error.TYPE,"les deux operants doivent etre entier");
        }
        type=Type.ENTIER;
        return null;
    }
    public Void visit(SaExpMult node) throws Exception {
        node.getOp1().accept(this);
        Type op1=type;
        node.getOp2().accept(this);
        Type op2=type;
        if(!(op1.nom().equals(op2.nom()) && op1.nom().equals("entier")))
        {
            throw new ErrorException(Error.TYPE,"les deux operants doivent etre entier");
        }
        type=Type.ENTIER;
        return null;
    }
    public Void visit(SaExpInt node)
    {
        type=Type.ENTIER;
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
    public Void visit(SaExpAppel node)throws Exception
    {
        node.getVal().accept(this);
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
        if (!(op1.nom().equals(op2.nom())))
            throw new ErrorException(Error.TYPE,"cannot affect to a differrent type");
        return null;
    }
    public Void visit(SaVarSimple node)
    {
        type=node.getTsItem().getType();
        return null;
    }
    public Void visit(SaExpAnd node) throws Exception
    {

        node.getOp1().accept(this);
        Type op1=type;
        node.getOp2().accept(this);
        Type op2=type;
        if (!(op1.nom().equals(op2.nom())) || !op1.nom().equals("bool"))
            throw new ErrorException(Error.TYPE,"And can be done only between two booleans");
        type=Type.BOOL;
        return null;
    }
    public Void visit(SaExpOr node) throws Exception
    {
        System.out.println("here");
        node.getOp1().accept(this);
        Type op1=type;
        node.getOp2().accept(this);
        Type op2=type;
        System.out.println(op2.nom());
        if (!(op1.nom().equals(op2.nom())) || !op1.nom().equals("bool"))
            throw new ErrorException(Error.TYPE,"And can be done only between two booleans");
        type=Type.BOOL;
        return null;
    }

    @Override
    public Void visit(SaVarIndicee node) throws Exception {
        type=node.getTsItem().getType();

        return null;
    }
    public Void visit(SaExpEqual node) throws Exception
    {
        type=Type.BOOL;
        return null;
    }
    public Void visit(SaExpInf node) throws Exception
    {
        node.getOp1().accept(this);
        Type op1=type;
        node.getOp2().accept(this);
        Type op2=type;
        if (!(op1.nom().equals(op2.nom())) || !op1.nom().equals("entier"))
            throw new ErrorException(Error.TYPE,"Operators must be of the same Type");
        type=Type.BOOL;
        return null;
    }
    @Override
    public Void visit(SaAppel node) throws Exception {
        if (node.getArguments()!=null)
        {
            SaLDecVar params= node.tsItem.saDecFonc.getParametres();
            SaLExp expparams=node.getArguments();
            while (expparams!=null)
            {
                SaExp exp=expparams.getTete();
                SaDecVar param= params.getTete();
                exp.accept(this);
                if (!type.nom().equals(param.getType().nom()))
                {
                    throw new ErrorException(Error.TYPE,"parametres pas de meme type");
                }
                expparams=expparams.getQueue();
                params=params.getQueue();
            }
        }

        type=node.getType();
        return null;
    }
    public Void visit(SaDecFonc node)
            throws Exception
    {
        fonctionCourante= node.tsItem;
        node.getCorps().accept(this);
        return null;
    }
    public Void visit(SaInstRetour node)throws Exception
    {

        node.getVal().accept(this);
        System.out.println(type.nom());
        System.out.println(fonctionCourante.typeRetour.nom());
        if (!type.nom().equals(fonctionCourante.typeRetour.nom()))
            throw new ErrorException(Error.TYPE,"Retour must be equal to function type");
        return null;
    }
    public Void visit(SaInstSi node)throws Exception
    {
        node.getTest().accept(this);
        if (!type.nom().equals("bool"))
            throw new ErrorException(Error.TYPE,"Test must be noolean");
        node.getAlors().accept(this);
        node.getSinon().accept(this);

        return null;
    }
    public Void visit(SaExpSub node) throws Exception
    {
        node.getOp1().accept(this);
        Type op1=type;
        node.getOp2().accept(this);
        Type op2=type;
        if(!(op1.nom().equals(op2.nom()) && op1.nom().equals("entier")))
        {
            throw new ErrorException(Error.TYPE,"les deux operants doivent etre entier");
        }
        type=Type.ENTIER;
        return null;
    }
    public Void visit(SaInstTantQue node) throws Exception
    {
        node.getTest().accept(this);
        if (!type.nom().equals("bool"))
            throw new ErrorException(Error.TYPE,"Test must be noolean");
        node.getFaire().accept(this);

        return null;
    }
    public Void visit(SaInstFaire node) throws Exception
    {
        node.getTest().accept(this);
        if (!type.nom().equals("bool"))
            throw new ErrorException(Error.TYPE,"Test must be noolean");
        node.getFaire().accept(this);

        return null;
    }

}
