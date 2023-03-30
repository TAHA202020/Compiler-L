import sc.analysis.*;
import sc.node.*;
import sa.*;
import util.Type;

import java.util.*;

public class Sc2sa extends DepthFirstAdapter
{

    SaLDecFonc fonctions=null;
    SaLDecVar varibalesglobales=null;
    private SaNode returnValue;
    private Type returnType;
    private PExpression returnExpression;

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        //System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        //System.out.println("</" + node.getClass().getSimpleName() + ">");
    }

    public SaNode getRoot()
    {
        return this.returnValue;
    }
    @Override
    public void caseAProgramme(AProgramme node) {
        if(node.getListedecvar() != null)
        {
            node.getListedecvar().apply(this);
            varibalesglobales=(SaLDecVar) returnValue;
        }
        node.getListedecfonc().apply(this);
        returnValue = new SaProg(varibalesglobales,fonctions);
    }
    @Override
    public void caseAListedecvar(AListedecvar node) {
        node.getVar().apply(this);
        SaDecVar op1 = (SaDecVar) returnValue;
        List<PVvar> copy = new ArrayList<PVvar>(node.getVvar());
        Collections.reverse(copy);
        SaLDecVar op2 = null;
        for(PVvar e : copy)
        {
            e.apply(this);
            op2 = new SaLDecVar((SaDecVar) returnValue,op2);
        }
        returnValue = new SaLDecVar(op1,op2);
    }
    @Override
    public void caseATabval(ATabval node) {
        returnValue = new SaDecTab(node.getIdentif().getText(),returnType,Integer.parseInt(node.getNombre().getText()));
    }
    @Override
    public void caseAVarSimpleVar(AVarSimpleVar node) {
        node.getType().apply(this);
        returnValue = new SaDecVarSimple(node.getIdentif().getText(), returnType);
    }
    @Override
    public void caseAVarSimpleVarname(AVarSimpleVarname node) {
        returnValue = new SaVarSimple(node.getIdentif().getText());
    }
    @Override
    public void caseABoolType(ABoolType node) {
        returnType = Type.BOOL;
    }
    @Override
    public void caseAEntierType(AEntierType node) {
        returnType=Type.ENTIER;
    }

    @Override
    public void caseAListedecfonc(AListedecfonc node) {
        node.getMain().apply(this);
        if(node.getFonction() != null){
            List<PFonction> copy = new ArrayList<>(node.getFonction());
            Collections.reverse(copy);
            for (PFonction f : copy) {
                f.apply(this);
            }

        }
    }

    @Override
    public void caseAFonction(AFonction node) {
        SaLDecVar arguments = null ;
        SaLDecVar vars = null ;
        if(node.getArguments() != null){
            node.getArguments().apply(this);
            arguments = (SaLDecVar) returnValue;
        }
        if(node.getListedecvar() != null){
            node.getListedecvar().apply(this);
            vars = (SaLDecVar) returnValue;
        }
        node.getBloc().apply(this);
        if(node.getType() != null){
            node.getType().apply(this);
        }else {
            returnType = Type.NUL ;
        }
        fonctions = new SaLDecFonc(new SaDecFonc(node.getIdentif().getText(),returnType,arguments,vars, (SaInstBloc) returnValue),fonctions);
    }

    @Override
    public void caseAMain(AMain node) {
        SaLDecVar vars=null;
        if (node.getListedecvar()!=null)
        {
            node.getListedecvar().apply(this);
            vars=(SaLDecVar) returnValue;
        }
        node.getBloc().apply(this);
        fonctions =new SaLDecFonc(new SaDecFonc("main",Type.NUL, null,vars,(SaInstBloc) returnValue),fonctions);
    }
    @Override
    public void caseABloc(ABloc node) {
        returnValue = null;
        List<PInstruction> copy = new ArrayList<PInstruction>(node.getInstruction());
        Collections.reverse(copy);
        SaLInst Instructions = null ;
        for(PInstruction e : copy)
        {
            e.apply(this);
            Instructions = new SaLInst((SaInst) returnValue , Instructions);
        }
        if(Instructions != null){
            returnValue = new SaInstBloc(Instructions);
        }
    }

    @Override
    public void caseAEcrire(AEcrire node) {
        node.getExpression().apply(this);
        returnValue = new SaInstEcriture((SaExp) returnValue);
    }

    @Override
    public void caseALire(ALire node) {
        node.getVarname().apply(this);
        returnValue = new SaInstAffect((SaVar)returnValue, new SaExpLire());
    }
    @Override
    public void caseAAffectation(AAffectation node) {
        node.getVarname().apply(this);
        SaVar var=(SaVar) returnValue;
        node.getExpression().apply(this);
        returnValue=new SaInstAffect(var,(SaExp) returnValue);
    }

    @Override
    public void caseASi(ASi node) {
        node.getExpression().apply(this);
        SaExp exp = (SaExp) returnValue;
        node.getBloc().apply(this);
        SaInst bloc = (SaInst) returnValue;
        SaInst sinonbloc = null;
        if(node.getSinonbloc() != null){
            node.getSinonbloc().apply(this);
            sinonbloc = (SaInst) returnValue;
        }
        returnValue = new SaInstSi(exp, bloc , sinonbloc);
    }

    @Override
    public void caseARetour(ARetour node) {
        node.getExpression().apply(this);
        returnValue = new SaInstRetour((SaExp) returnValue);
    }

    @Override
    public void caseATantque(ATantque node) {
        node.getExpression().apply(this);
        SaExp test = (SaExp) returnValue;
        node.getBloc().apply(this);
        SaInst faire = (SaInst) returnValue;
        returnValue = new SaInstTantQue(test, faire);
    }

    @Override
    public void caseAAppel(AAppel node) {
        SaLExp lExp = null;
        if(node.getExpressionvexp() != null){
            node.getExpressionvexp().apply(this);
            lExp = (SaLExp) returnValue;

        }
        returnValue = new SaAppel(node.getIdentif().getText(), lExp);
    }



    @Override
    public void caseAMultiExpou(AMultiExpou node) {
        node.getExpet().apply(this);
        SaExp op1 = (SaExp) returnValue;
        node.getExpou().apply(this);
        SaExp op2 = (SaExp) returnValue;
        returnValue = new SaExpOr(op2 , op1);
    }
    @Override
    public void caseAMultiExpet(AMultiExpet node) {
        node.getExpnon().apply(this);
        SaExp op1 = (SaExp) returnValue;
        node.getExpet().apply(this);
        SaExp op2 = (SaExp) returnValue;
        returnValue = new SaExpAnd(op2 , op1);
    }

    @Override
    public void caseAMultiExpnon(AMultiExpnon node) {
        node.getExpComparaisonInf().apply(this);
        SaExp op1 = (SaExp) returnValue;
        List<TNot> copy = new ArrayList<>(node.getNot());
        returnValue = new SaExpNot(op1);
        for(int i =1 ; i< copy.size(); i++)
        {
            copy.get(i).apply(this);
            returnValue = new SaExpNot((SaExp) returnValue);
        }
    }

    @Override
    public void caseAMultiExpComparaisonInf(AMultiExpComparaisonInf node) {
        node.getExpComparaisonEgale().apply(this);
        SaExp op1 = (SaExp) returnValue;
        node.getExpComparaisonInf().apply(this);
        SaExp op2 = (SaExp) returnValue;
        returnValue = new SaExpInf(op2 , op1);
    }

    @Override
    public void caseAMultiExpComparaisonEgale(AMultiExpComparaisonEgale node) {
        node.getExpAddition().apply(this);
        SaExp op1 = (SaExp) returnValue;
        node.getExpComparaisonEgale().apply(this);
        SaExp op2 = (SaExp) returnValue;
        returnValue = new SaExpEqual(op2 , op1);
    }

    @Override
    public void caseAMultiExpAddition(AMultiExpAddition node) {
        node.getExpSub().apply(this);
        SaExp op1 = (SaExp) returnValue;
        node.getExpAddition().apply(this);
        SaExp op2 = (SaExp) returnValue;
        returnValue = new SaExpAdd(op2 , op1);
    }

    @Override
    public void caseAMultiExpSub(AMultiExpSub node) {
        node.getExpMultiplication().apply(this);
        SaExp op1 = (SaExp) returnValue;
        node.getExpSub().apply(this);
        SaExp op2 = (SaExp) returnValue;
        returnValue = new SaExpSub(op2 , op1);

    }

    @Override
    public void caseAMultiExpMultiplication(AMultiExpMultiplication node) {
        node.getExpDiv().apply(this);
        SaExp op1 = (SaExp) returnValue;
        node.getExpMultiplication().apply(this);
        SaExp op2 = (SaExp) returnValue;
        returnValue = new SaExpMult(op2 , op1);
    }

    @Override
    public void caseAMultiExpDiv(AMultiExpDiv node) {
        node.getExpressionPrimaire().apply(this);
        SaExp op1 = (SaExp) returnValue;
        node.getExpDiv().apply(this);
        SaExp op2 = (SaExp) returnValue;
        returnValue = new SaExpDiv(op2 , op1);

    }

    @Override
    public void caseAValBoolExpressionPrimaire(AValBoolExpressionPrimaire node) {
        node.getValeurbool().apply(this);
    }

    @Override
    public void caseANombreExpressionPrimaire(ANombreExpressionPrimaire node) {
        returnValue= new SaExpInt(Integer.parseInt(node.getNombre().getText()));
    }

    @Override
    public void caseAVarExpressionPrimaire(AVarExpressionPrimaire node) {
        node.getVarname().apply(this);
        SaVar var = (SaVar) returnValue;
        returnValue = new SaExpVar(var);
    }

    @Override
    public void caseAVarTabVarname(AVarTabVarname node) {
        node.getExpression().apply(this);
        returnValue = new SaVarIndicee(node.getIdentif().getText(), (SaExp) returnValue);
    }

    @Override
    public void caseAFauxValeurbool(AFauxValeurbool node) {
        returnValue = new SaExpFaux();
    }

    @Override
    public void caseAVraiValeurbool(AVraiValeurbool node) {
        returnValue = new SaExpVrai();
    }

    @Override
    public void caseAAppelExpressionPrimaire(AAppelExpressionPrimaire node) {
        node.getAppelSansPv().apply(this);
    }

    @Override
    public void caseAAppelSansPv(AAppelSansPv node) {
        SaLExp lExp = null;
        if(node.getExpressionvexp() != null){
            node.getExpressionvexp().apply(this);
            lExp = (SaLExp) returnValue;

        }
        returnValue = new SaExpAppel(new SaAppel(node.getIdentif().getText(), lExp));
    }


    @Override
    public void caseAExpressionvexp(AExpressionvexp node) {
        node.getExpression().apply(this);
        SaExp op1 = (SaExp) returnValue;
        List<PVexpression> copy = new ArrayList<>(node.getVexpression());
        Collections.reverse(copy);
        SaLExp op2 = null;
        for(PVexpression e : copy)
        {
            e.apply(this);
            op2 = new SaLExp((SaExp) returnValue,op2);
        }
        returnValue = new SaLExp(op1,op2);
    }
}
