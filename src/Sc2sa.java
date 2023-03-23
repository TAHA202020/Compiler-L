import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;
import util.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sc2sa extends DepthFirstAdapter {
    int size;
    SaLDecFonc functions=null;
    SaLDecVar variables=null;
    String operation;
    private SaNode returnValue;
    private Type returnType;
    //programme=main

    public SaNode getRoot()
    {
        return this.returnValue;
    }


    //functionglobalvar={globalvar} parameters|{function}function ;


    @Override
    public void caseAProgramme(AProgramme node) {
        if (node.getParameters()!=null)
        {
            node.getParameters().apply(this);
            variables=(SaLDecVar) returnValue;
        }
        node.getMain().apply(this);
        if (node.getFunction()!=null)
        {
            List<PFunction> pFunctions=new ArrayList<>(node.getFunction());
            Collections.reverse(pFunctions);
            for (PFunction function:pFunctions)
            {
                function.apply(this);
            }
        }
        returnValue=new SaProg(variables,functions);
    }

    @Override
    public void caseARightbracketsValue(ARightbracketsValue node) {
        node.getFirstvalue().apply(this);
        SaExp op1=(SaExp) returnValue;
        node.getSecondvalue().apply(this);
        SaExp op2=(SaExp) returnValue;
        node.getOperations().apply(this);
        if(operation=="plus")
        {
            returnValue = new SaExpAdd(op1,op2);
        }
        if(operation=="minus")
        {

            returnValue = new SaExpSub(op1,op2);
        }
        if(operation=="div")
        {

            returnValue = new SaExpDiv(op1,op2);
        }
        if(operation=="mult")
        {

            returnValue = new SaExpMult(op1,op2);
        }
        if(operation=="bigger")
        {

            returnValue = new SaExpInf(op2,op1);
        }
        if(operation=="smaller")
        {

            returnValue = new SaExpInf(op1,op2);
        }
        if(operation=="equal")
        {

            returnValue = new SaExpEqual(op1,op2);
        }
        if(operation=="notequal")
        {

            returnValue = new SaExpNot(new SaExpEqual(op1,op2));
        }
        if(operation=="or")
        {

            returnValue = new SaExpOr(op1,op2);
        }
        if(operation=="and")
        {

            returnValue = new SaExpAnd(op1,op2);
        }
    }

    //function=type? identif [firstlp]:lp [funcparams]:parameters? [firstrp]:rp [declaration]:parameters? startfunc instruction* endfunc;
    //public SaDecFonc(String nom, Type typeRetour, SaLDecVar parametres, SaLDecVar variables, SaInst corps)
    public void caseAFunction(AFunction node)
    {
        inAFunction(node);
        Type typetoreturn;
        if(node.getType() != null)
        {
            node.getType().apply(this);
            typetoreturn=returnType;
        }
        else
        {
            typetoreturn=Type.NUL;
        }

        String name=node.getIdentif().getText();
        SaLDecVar parametres=null;
        SaLDecVar variables=null;
        if(node.getFuncparams() != null)
        {
            node.getFuncparams().apply(this);
            parametres=(SaLDecVar) returnValue;
        }
        if(node.getDeclaration() != null)
        {
            node.getDeclaration().apply(this);
            variables=(SaLDecVar) returnValue;
        }
        List<PInstruction> copy = new ArrayList<>(node.getInstruction());
        Collections.reverse(copy);
        SaLInst saLInst=null;
        for(PInstruction e : copy)
        {
            e.apply(this);
            saLInst=new SaLInst((SaInst) returnValue,saLInst);
        }
        SaInstBloc bloc;
        if (saLInst==null)
        {
            bloc=null;
        }
        else
            bloc=new SaInstBloc(saLInst);
        functions =new SaLDecFonc(new SaDecFonc(name,typetoreturn,parametres,variables, bloc),functions);
        outAFunction(node);
    }
    //main = mainfunc parameters? startfunc instruction* endfunc;
    public void caseAMain(AMain node)
    {
        inAMain(node);

        List<PInstruction> copy = new ArrayList<>(node.getInstruction());
        Collections.reverse(copy);
        SaLInst saLInst=null;
        for(PInstruction e : copy)
        {
            e.apply(this);
            saLInst=new SaLInst((SaInst) returnValue,saLInst);
        }
        SaInstBloc bloc;
        if (saLInst==null)
        {
            bloc=null;
        }
        else
            bloc=new SaInstBloc(saLInst);
        if(node.getParameters() != null)
        {
            node.getParameters().apply(this);
        }
        else
            returnValue =null;
        functions= new SaLDecFonc(new SaDecFonc("main",Type.NUL,null,(SaLDecVar) returnValue,bloc),functions);
        outAMain(node);
    }
    //parameters = parametervirgul* parameter;
    public void caseAParameters(AParameters node) {
        inAParameters(node);
        node.getParameter().apply(this);
        SaLDecVar decVar=new SaLDecVar((SaDecVar) returnValue,null);
        if (node.getParametervirgul()!=null)
        {
            List<PParametervirgul> copy= new ArrayList<>(node.getParametervirgul());
            Collections.reverse(copy);
            for (PParametervirgul e:copy)
            {
                e.apply(this);
                decVar=new SaLDecVar((SaDecVar)returnValue,decVar);
            }
        }
        returnValue =  decVar ;
        outAParameters(node);
    }
    //parametervirgul= parameter virgul;
    public void caseAParametervirgul(AParametervirgul node)
    {
        inAParametervirgul(node);
        node.getParameter().apply(this);
        outAParametervirgul(node);
    }
    //parameter= type identif tablesize?;
    public void caseAParameter(AParameter node) {
        inAParameter(node);
        if (node.getTablesize()!=null)
        {
            node.getType().apply(this);
            Type type=returnType;
            returnValue=new SaDecTab(node.getIdentif().getText(),type,size);
        }
        else
        {
            node.getType().apply(this);
            Type type=returnType;
            returnValue =new SaDecVarSimple(node.getIdentif().getText(),type);
        }
        outAParameter(node);
    }
    //tablesize=rb nombre? lb;


    @Override
    public void caseATablesize(ATablesize node) {
        size=Integer.parseInt(node.getNombre().getText());
    }

    public void caseABooleanType(ABooleanType node)
    {
        inABooleanType(node);
        this.returnType = Type.BOOL;
        outABooleanType(node);
    }
    public void caseAEntierType(AEntierType node)
    {
        inAEntierType(node);
        this.returnType = Type.ENTIER;
        outAEntierType(node);
    }
    //instructions
    //instruction={affectation}affectation pv|{condition}condition|{callfunc} identif lp nparam? rp pv|{read} identif tablevalues? equal read pv |{write} write lp value rp pv | {returnstatement} returnstatement|{loop} loop;

    SaLExp parameters=null;
    //nparam={multiparam} value virgul nparam |{singleparam} value;
    public void caseASingleparamNparam(ASingleparamNparam node) {
        node.getValue().apply(this);
        parameters=new SaLExp((SaExp) returnValue,parameters);
    }

    @Override
    public void caseAMultiparamNparam(AMultiparamNparam node) {
        node.getValue().apply(this);
        parameters=new SaLExp((SaExp) returnValue,parameters);
        node.getNparam().apply(this);
    }

    @Override
    public void caseACallfuncInstruction(ACallfuncInstruction node) {
        String name=node.getIdentif().getText();
        if (node.getNparam()!=null)
        {
            node.getNparam().apply(this);
        }

        SaLExp arguments=parameters;
        parameters=null;
        returnValue=new SaAppel(name,arguments);
    }

    @Override
    public void caseAReadInstruction(AReadInstruction node) {
        inAReadInstruction(node);
        SaVar var;
        if (node.getTablevalues()!=null)
        {
            node.getTablevalues().apply(this);
            var=new SaVarIndicee(node.getIdentif().getText(),(SaExp) returnValue);
        }
        else
            var=new SaVarSimple(node.getIdentif().getText());
        returnValue=new SaInstAffect(var,new SaExpLire());
        outAReadInstruction(node);
    }

    @Override
    public void caseAWriteInstruction(AWriteInstruction node) {
        node.getValue().apply(this);
        returnValue=new SaInstEcriture((SaExp) returnValue);
    }
    public void caseAAffectationInstruction(AAffectationInstruction node)
    {
        inAAffectationInstruction(node);
        if(node.getAffectation() != null)
        {
            node.getAffectation().apply(this);
        }
        outAAffectationInstruction(node);
    }

    @Override
    public void caseAReturnstatementInstruction(AReturnstatementInstruction node) {
        node.getReturnstatement().apply(this);
    }

    //affectation=identif tablevalues? equal value;
    public void caseAAffectation(AAffectation node) {
        inAAffectation(node);
        SaVar op1;
        if (node.getTablevalues()!=null)
        {
            node.getTablevalues().apply(this);
            op1=new SaVarIndicee(node.getIdentif().getText(),(SaExp) returnValue);
        }
        else {
            op1 = new SaVarSimple(node.getIdentif().getText());
        }
        node.getValue().apply(this);
        returnValue =new SaInstAffect(op1,(SaExp) returnValue);
        outAAffectation(node);
    }
    //singlevalue={identif} identif tablevalues? |{booleans} booleans | {nombre} nombre| {callfunction} identif lp nparam? rp;
    //value= {multiplevalues} singlevalue operations value| {singlevalue} singlevalue | {brakets} lp value rp | {notvalue} not value;
    public void caseANotvalueValue(ANotvalueValue node)
    {
        inANotvalueValue(node);
        node.getValue().apply(this);
        returnValue =new SaExpNot((SaExp) returnValue);
        outANotvalueValue(node);
    }

    @Override
    public void caseACallfunctionSinglevalue(ACallfunctionSinglevalue node) {
        SaLExp params=null;
        if (node.getNparam()!=null)
        {
            node.getNparam().apply(this);
            params=(SaLExp)returnValue;
        }

        returnValue=new SaExpAppel(new SaAppel(node.getIdentif().getText(),params));
    }

    @Override
    public void caseAIdentifSinglevalue(AIdentifSinglevalue node) {

        if (node.getTablevalues()!=null)
        {
            node.getTablevalues().apply(this);
            returnValue=new SaExpVar(new SaVarIndicee(node.getIdentif().getText(),(SaExp) returnValue));
        }
        else returnValue=new SaExpVar(new SaVarSimple(node.getIdentif().getText()));

    }

    @Override
    public void caseABooleansSinglevalue(ABooleansSinglevalue node) {
        node.getBooleans().apply(this);
    }

    @Override
    public void caseAFalseBooleans(AFalseBooleans node) {
        returnValue=new SaExpFaux();
    }

    @Override
    public void caseATrueBooleans(ATrueBooleans node) {
        returnValue=new SaExpVrai();
    }

    @Override
    public void caseANombreSinglevalue(ANombreSinglevalue node) {
        returnValue=new SaExpInt(Integer.parseInt(node.getNombre().getText()));
    }

    @Override
    public void caseABraketsValue(ABraketsValue node) {
        node.getValue().apply(this);
    }

    public void caseAMultiplevaluesValue(AMultiplevaluesValue node)
    {
        inAMultiplevaluesValue(node);
        node.getSinglevalue().apply(this);
        SaExp op1=(SaExp) returnValue;
        node.getValue().apply(this);
        SaExp op2=(SaExp) returnValue;
        node.getOperations().apply(this);
        if(operation=="plus")
        {
            returnValue = new SaExpAdd(op1,op2);
        }
        if(operation=="minus")
        {
            returnValue = new SaExpSub(op1,op2);
        }
        if(operation=="div")
        {

            returnValue = new SaExpDiv(op1,op2);
        }
        if(operation=="mult")
        {

            returnValue = new SaExpMult(op1,op2);
        }
        if(operation=="bigger")
        {

            returnValue = new SaExpInf(op2,op1);
        }
        if(operation=="smaller")
        {

            returnValue = new SaExpInf(op1,op2);
        }
        if(operation=="equal")
        {

            returnValue = new SaExpEqual(op1,op2);
        }
        if(operation=="notequal")
        {

            returnValue = new SaExpNot(new SaExpEqual(op1,op2));
        }
        if(operation=="or")
        {

            returnValue = new SaExpOr(op1,op2);
        }
        if(operation=="and")
        {

            returnValue = new SaExpAnd(op1,op2);
        }
        outAMultiplevaluesValue(node);
    }

    @Override
    public void caseAOpOperations(AOpOperations node) {
        inAOpOperations(node);
        node.getOp().apply(this);
        outAOpOperations(node);
    }

    @Override
    public void caseAPlusOp(APlusOp node) {
        operation="plus";
    }

    @Override
    public void caseADivOp(ADivOp node) {
        operation="div";
    }

    @Override
    public void caseAMinusOp(AMinusOp node) {
        operation="minus";
    }

    @Override
    public void caseAMultOp(AMultOp node) {
        operation="mult";
    }

    @Override
    public void caseALogOperations(ALogOperations node) {
        inALogOperations(node);
        node.getLog().apply(this);
        outALogOperations(node);
    }

    @Override
    public void caseAAndLog(AAndLog node) {
        operation="and";
    }

    @Override
    public void caseAOrLog(AOrLog node) {
        operation="or";
    }

    @Override
    public void caseACmpOperations(ACmpOperations node) {
        inACmpOperations(node);
        node.getCmp().apply(this);
        outACmpOperations(node);
    }

    @Override
    public void caseABiggerCmp(ABiggerCmp node) {
        operation="bigger";
    }

    @Override
    public void caseASmallerCmp(ASmallerCmp node) {
        operation="smaller";
    }

    @Override
    public void caseAEqualCmp(AEqualCmp node) {
        operation="equal";
    }

    @Override
    public void caseANotequalCmp(ANotequalCmp node) {
        operation="notequal";
    }


    //realtablevalue={identif}identif | {nombre}nombre | {tablevalue}identif tablevalues;
    //tablevalue= {multiple} realtablevalue op tablevalue | {single} realtablevalue;
    //tablevalues=rb tablevalue lb ;

    public void caseATablevalues(ATablevalues node) {
        node.getTablevalue().apply(this);
    }

    @Override
    public void caseAMultipleTablevalue(AMultipleTablevalue node) {
        inAMultipleTablevalue(node);
        node.getOp().apply(this);
        node.getRealtablevalue().apply(this);
        SaExp op1= (SaExp) returnValue;
        node.getTablevalue().apply(this);
        SaExp op2 =(SaExp) returnValue;
        if(operation=="plus")
        {
            returnValue = new SaExpAdd(op1,op2);
        }
        if(operation=="minus")
        {

            returnValue = new SaExpSub(op1,op2);
        }
        if(operation=="div")
        {

            returnValue = new SaExpDiv(op1,op2);
        }
        if(operation=="mult")
        {

            returnValue = new SaExpMult(op1,op2);
        }
        outAMultipleTablevalue(node);
    }
    //{identif}identif | {nombre}nombre | {tablevalue}identif tablevalues;
    public void caseAIdentifRealtablevalue(AIdentifRealtablevalue node) {

        returnValue=new SaExpVar(new SaVarSimple(node.getIdentif().getText()));
    }

    public void caseANombreRealtablevalue(ANombreRealtablevalue node) {
        returnValue=new SaExpInt(Integer.parseInt(node.getNombre().getText()));
    }

    public void caseATablevalueRealtablevalue(ATablevalueRealtablevalue node) {
        node.getTablevalues().apply(this);
        returnValue =new SaExpVar(new SaVarIndicee(node.getIdentif().getText(),(SaExp) returnValue));
    }
    //returnstatement=return value pv;

    @Override
    public void caseAReturnstatement(AReturnstatement node) {
        node.getValue().apply(this);
        returnValue=new SaInstRetour((SaExp) returnValue);
    }
    //Conditions
    //condition = si value alors startfunc instruction* endfunc elsecondition?;
    //elsecondition=else startfunc instruction* endfunc;

    @Override
    public void caseACondition(ACondition node) {
        node.getValue().apply(this);
        SaExp test=(SaExp) returnValue;
        List<PInstruction> copy = new ArrayList<>(node.getInstruction());
        Collections.reverse(copy);
        SaLInst saLInst=null;
        for(PInstruction e : copy)
        {
            e.apply(this);
            saLInst=new SaLInst((SaInst) returnValue,saLInst);
        }
        SaInstBloc alors=null;
        if (saLInst!=null)
        {
             alors=new SaInstBloc(saLInst);
        }
        returnValue=null;
        if (node.getElsecondition()!=null)
        {
            node.getElsecondition().apply(this);
        }
        returnValue=new SaInstSi(test,alors,(SaInst) returnValue);
    }

    @Override
    public void caseAElsecondition(AElsecondition node) {
        List<PInstruction> copy = new ArrayList<>(node.getInstruction());
        Collections.reverse(copy);
        SaLInst saLInst=null;
        for(PInstruction e : copy)
        {
            e.apply(this);
            saLInst=new SaLInst((SaInst) returnValue,saLInst);
        }
        returnValue=new SaInstBloc(saLInst);
    }
    //loop=while  value  do startfunc instruction* endfunc;

    @Override
    public void caseALoop(ALoop node) {
        node.getValue().apply(this);
        SaExp test=(SaExp) returnValue;
        List<PInstruction> copy = new ArrayList<>(node.getInstruction());
        Collections.reverse(copy);
        SaLInst saLInst=null;
        for(PInstruction e : copy)
        {
            e.apply(this);
            saLInst=new SaLInst((SaInst) returnValue,saLInst);
        }
        SaInst faire=new SaInstBloc(saLInst);
        returnValue=new SaInstTantQue(test,faire);

    }
}


