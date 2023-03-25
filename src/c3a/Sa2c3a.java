package c3a;
import java.util.*;
import ts.*;
import sa.*;

public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {
    private C3a c3a;
    int indentation;
    public C3a getC3a(){return this.c3a;}
    
    public Sa2c3a(SaNode root, Ts tableGlobale){
	c3a = new C3a();
	C3aTemp result = c3a.newTemp();
	C3aFunction fct = new C3aFunction(tableGlobale.getFct("main"));
	c3a.ajouteInst(new C3aInstCall(fct, result, ""));
	c3a.ajouteInst(new C3aInstStop(result, ""));
	
	indentation = 0;
	try{
	    root.accept(this);
	}
	catch(Exception e){}
    }

    public void defaultIn(SaNode node)
    {
	//for(int i = 0; i < indentation; i++){System.out.print(" ");}
	//indentation++;
	//System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(SaNode node)
    {
	//indentation--;
	//	for(int i = 0; i < indentation; i++){System.out.print(" ");}
	//	System.out.println("</" + node.getClass().getSimpleName() + ">");
    }

    @Override
    public C3aOperand visit(SaExpAdd node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstAdd(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }
    @Override
    public C3aOperand visit(SaExpDiv node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstDiv(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }
    @Override
    public C3aOperand visit(SaExpSub node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstSub(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }
    @Override
    public C3aOperand visit(SaExpMult node) throws Exception
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstMult(op1, op2, result, ""));
        defaultOut(node);
        return result;
    }
    public C3aOperand visit(SaExpInt node)
    {
        return new C3aConstant(node.getVal());
    }

    public C3aOperand visit(SaAppel node) throws Exception
    {
        if (node.getArguments()!=null)
        {
            SaLExp arguments=node.getArguments();
            while (arguments!=null)
            {
                SaExp arg=arguments.getTete();
                c3a.ajouteInst(new C3aInstParam(arg.accept(this),""));
                arguments=arguments.getQueue();
            }
        }
        C3aOperand result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstCall(new C3aFunction(node.tsItem),result,""));
        return result;
    }
    public C3aOperand visit(SaDecFonc node)throws Exception
    {

        c3a.ajouteInst(new C3aInstFBegin(node.tsItem,""));
        if (node.getCorps()!=null)
        {
            node.getCorps().accept(this);
        }
        c3a.ajouteInst(new C3aInstFEnd(""));
        return null;
    }
    public C3aOperand visit(SaInstAffect node) throws Exception
    {
        C3aOperand var=node.getLhs().accept(this);
        C3aOperand value=node.getRhs().accept(this);
        c3a.ajouteInst(new C3aInstAffect(value,var,""));
        return null;
    }
    public C3aOperand visit(SaInstRetour node)throws Exception
    {
        C3aOperand returnval=node.getVal().accept(this);
        c3a.ajouteInst(new C3aInstReturn(returnval,""));
        return returnval;
    }
    public C3aOperand visit(SaExpLire node)
    {
        C3aOperand temp=c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(temp, ""));
        return temp;
    }
    public C3aOperand visit(SaVarSimple node)
    {
        if(node.tsItem==null)
            System.out.println("null");
        return new C3aVar(node.tsItem,null);
    }
    public C3aOperand visit(SaVarIndicee node)throws Exception
    {

        C3aOperand index=node.getIndice().accept(this);
        return new C3aVar(node.getTsItem(),index);
    }
    public C3aOperand visit(SaInstEcriture node)throws Exception
    {
        C3aOperand op=node.getArg().accept(this);
        c3a.ajouteInst(new C3aInstWrite(op,""));
        return null;
    }
    public C3aOperand visit(SaExpVar node) throws Exception
    {
        return node.getVar().accept(this);
    }
    public C3aOperand visit(SaExpVrai node)
    {
        return c3a.True;
    }
    public C3aOperand visit(SaExpFaux node)
    {
        return c3a.False;
    }
    public C3aOperand visit(SaExpInf node)throws Exception
    {
        C3aOperand value=c3a.newTemp();
        C3aOperand label=c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect( c3a.True,value,""));
        c3a.ajouteInst(new C3aInstJumpIfLess(node.getOp1().accept(this),node.getOp2().accept(this),label,""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False,value, ""));
        c3a.addLabelToNextInst((C3aLabel) label);
        return value;
    }
    public C3aOperand visit(SaExpAnd node)throws Exception
    {
        C3aOperand value=c3a.newTemp();
        C3aOperand label1=c3a.newAutoLabel();
        C3aOperand label2=c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect( c3a.True,value,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp1().accept(this), c3a.True,label1 ,""));
        c3a.ajouteInst(new C3aInstAffect( c3a.False,value,""));
        c3a.addLabelToNextInst((C3aLabel) label1);
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp2().accept(this), c3a.True,label2 ,""));
        c3a.ajouteInst(new C3aInstAffect( c3a.False,value,""));
        c3a.addLabelToNextInst((C3aLabel) label2);
        return value;
    }
    public C3aOperand visit(SaExpOr node)throws Exception
    {
        C3aOperand value=c3a.newTemp();
        C3aOperand label=c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect( c3a.True,value,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp1().accept(this), c3a.True,label ,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp2().accept(this), c3a.True,label ,""));
        c3a.ajouteInst(new C3aInstAffect( c3a.False,value,""));
        c3a.addLabelToNextInst((C3aLabel) label);
        return value;
    }
    public C3aOperand visit(SaExpEqual node)throws Exception
    {
        C3aOperand value=c3a.newTemp();
        C3aOperand label=c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect( c3a.True,value,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp1().accept(this), node.getOp2().accept(this),label ,""));
        c3a.ajouteInst(new C3aInstAffect( c3a.False,value,""));
        c3a.addLabelToNextInst((C3aLabel) label);
        return value;
    }

    public C3aOperand visit(SaExpNot node)throws Exception
    {
        C3aOperand value=c3a.newTemp();
        C3aOperand label1=c3a.newAutoLabel();
        C3aOperand label2=c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect( node.getOp1().accept(this),value,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(node.getOp1().accept(this), c3a.True,label1 ,""));
        c3a.ajouteInst(new C3aInstAffect(c3a.True, value,""));
        c3a.ajouteInst(new C3aInstJump(label2,""));
        c3a.addLabelToNextInst((C3aLabel) label1);
        c3a.ajouteInst(new C3aInstAffect(c3a.False, value,""));
        c3a.addLabelToNextInst((C3aLabel) label2);
        return value;
    }
    public C3aOperand visit(SaInstSi node)throws Exception
    {
        C3aOperand test=c3a.newTemp();
        C3aOperand value=node.getTest().accept(this);
        c3a.ajouteInst(new C3aInstAffect(value,test,""));
        C3aOperand alors=c3a.newAutoLabel();
        C3aOperand sinon= c3a.newAutoLabel();
        C3aOperand endsi= c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.True,alors,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False,sinon,""));
        if (node.getAlors()!=null)
        {
            c3a.addLabelToNextInst((C3aLabel) alors);
            node.getAlors().accept(this);
        }
        c3a.ajouteInst(new C3aInstJump(endsi,""));
        if (node.getSinon()!=null)
        {
            c3a.addLabelToNextInst((C3aLabel) sinon);
            node.getSinon().accept(this);
        }
        c3a.addLabelToNextInst((C3aLabel) endsi);
        return null;
    }
    public C3aOperand visit(SaInstTantQue node)throws Exception
    {
        C3aOperand startloop= c3a.newAutoLabel();
        C3aOperand endloop=c3a.newAutoLabel();
        c3a.addLabelToNextInst((C3aLabel) startloop);
        C3aOperand test=c3a.newTemp();
        C3aOperand value=node.getTest().accept(this);
        c3a.ajouteInst(new C3aInstAffect(value,test,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False,endloop,""));
        if (node.getFaire()!=null)
        {
            node.getFaire().accept(this);
        }
        c3a.ajouteInst(new C3aInstJump(startloop,""));
        c3a.addLabelToNextInst((C3aLabel) endloop);
        return null;
    }
}
