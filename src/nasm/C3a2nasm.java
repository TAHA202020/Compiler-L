package nasm;
import java.util.*;
import ts.*;
import c3a.*;

public class C3a2nasm implements C3aVisitor <NasmOperand> {
    private C3a c3a;
    private Nasm nasm;
    private Ts tableGlobale;
    private TsItemFct currentFct;
    private NasmRegister esp;
    private NasmRegister ebp;

    
    public C3a2nasm(C3a c3a, Ts tableGlobale){
	this.c3a = c3a;
	nasm = new Nasm(tableGlobale);
	nasm.setTempCounter(c3a.getTempCounter());
	
	this.tableGlobale = tableGlobale;
	this.currentFct = null;
	esp = new NasmRegister(-1);
	esp.colorRegister(Nasm.REG_ESP);

	ebp = new NasmRegister(-1);
	ebp.colorRegister(Nasm.REG_EBP);

	NasmOperand res;
	for(C3aInst c3aInst : c3a.listeInst){
	    //	   	    System.out.println("<" + c3aInst.getClass().getSimpleName() + ">");
	    res = c3aInst.accept(this);
	}
    }

    public Nasm getNasm(){return nasm;}

    /*--------------------------------------------------------------------------------------------------------------
      transforme une opérande trois adresses en une opérande asm selon les règles suivantes :
      
      C3aConstant -> NasmConstant
      C3aTemp     -> NasmRegister
      C3aLabel    -> NasmLabel
      C3aFunction -> NasmLabel
      C3aVar      -> NasmAddress
      --------------------------------------------------------------------------------------------------------------*/

	@Override
	public NasmOperand visit(C3aInstAdd inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmOperand oper1 = inst.op1.accept(this);
		NasmOperand oper2 = inst.op2.accept(this);
		NasmOperand dest = inst.result.accept(this);
		nasm.ajouteInst(new NasmMov(label, dest, oper1, ""));
		nasm.ajouteInst(new NasmAdd(null, dest, oper2, ""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstCall inst) {
		NasmOperand register=nasm.newRegister();
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmSub(label,esp,new NasmConstant(4),"allocation mémoire pour la valeur de retour"));
		nasm.ajouteInst(new NasmCall(null,new NasmLabel(inst.op1.val.identif),""));
		nasm.ajouteInst(new NasmPop(null,register,"récupération de la valeur de retour"));
		return register;
	}

	@Override
	public NasmOperand visit(C3aInstFBegin inst) {
		currentFct=inst.val;
		nasm.ajouteInst(new NasmPush(new NasmLabel(currentFct.identif),ebp,"sauvegarde la valeur de ebp"));
		nasm.ajouteInst(new NasmMov(null,ebp,esp,"nouvelle valeur de ebp"));
		NasmRegister regeax=new NasmRegister(-1);
		NasmRegister regebx=new NasmRegister(-1);
		NasmRegister regecx=new NasmRegister(-1);
		NasmRegister regedx=new NasmRegister(-1);
		regeax.colorRegister(Nasm.REG_EAX);
		regebx.colorRegister(Nasm.REG_EBX);
		regecx.colorRegister(Nasm.REG_ECX);
		regedx.colorRegister(Nasm.REG_EDX);
		nasm.ajouteInst(new NasmPush(null,regeax,"sauvegarde de eax"));
		nasm.ajouteInst(new NasmPush(null,regebx,"sauvegarde de ebx"));
		nasm.ajouteInst(new NasmPush(null,regecx,"sauvegarde de ecx"));
		nasm.ajouteInst(new NasmPush(null,regedx,"sauvegarde de edx"));
		nasm.ajouteInst(new NasmSub(null,esp,new NasmConstant(currentFct.table.getAdrVarCourante()),"allocation des variables locales"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInst inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfLess inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstMult inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstRead inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstSub inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstAffect inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmOperand op1=inst.op1.accept(this);
		NasmOperand op2=inst.result.accept(this);
		nasm.ajouteInst(new NasmMov(label,op2,op1,"Affectation"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstDiv inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstFEnd inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		nasm.ajouteInst(new NasmAdd(label,esp,new NasmConstant(currentFct.table.getAdrVarCourante()),"désallocation des variables locales"));
		NasmRegister regeax=new NasmRegister(-1);
		NasmRegister regebx=new NasmRegister(-1);
		NasmRegister regecx=new NasmRegister(-1);
		NasmRegister regedx=new NasmRegister(-1);
		regeax.colorRegister(Nasm.REG_EAX);
		regebx.colorRegister(Nasm.REG_EBX);
		regecx.colorRegister(Nasm.REG_ECX);
		regedx.colorRegister(Nasm.REG_EDX);
		nasm.ajouteInst(new NasmPop(null,regedx,"restaure edx"));
		nasm.ajouteInst(new NasmPop(null,regecx,"restaure ecx"));
		nasm.ajouteInst(new NasmPop(null,regebx,"restaure ebx"));
		nasm.ajouteInst(new NasmPop(null,regeax,"restaure eax"));
		nasm.ajouteInst(new NasmPop(null,ebp,"restaure la valeur de ebp"));
		nasm.ajouteInst(new NasmRet(null,""));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfEqual inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstJump inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstParam inst) {
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstReturn inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmOperand op=inst.op1.accept(this);
		nasm.ajouteInst(new NasmMov(label,new NasmAddress(ebp,'+',new NasmConstant(8)),op,"retour valeur"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstWrite inst) {
		NasmOperand label = (inst.label != null) ? inst.label.accept(this) : null;
		NasmOperand op=inst.op1.accept(this);
		NasmRegister regeax=new NasmRegister(-1);
		regeax.colorRegister(Nasm.REG_EAX);
		nasm.ajouteInst(new NasmMov(label,regeax,op,"Write 1"));
		nasm.ajouteInst(new NasmCall(null,new NasmLabel("iprintLF"),"Write 2"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aInstStop inst) {
		NasmRegister registerebx=new NasmRegister(-1);
		registerebx.colorRegister(Nasm.REG_EBX);
		NasmRegister registereax=new NasmRegister(-1);
		registereax.colorRegister(Nasm.REG_EAX);
		nasm.ajouteInst(new NasmMov(null,registerebx,new NasmConstant(0),"valeur de retour du programme"));
		nasm.ajouteInst(new NasmMov(null,registereax,new NasmConstant(1),"code de sortie"));
		nasm.ajouteInst(new NasmInt(null,"sys call"));
		return null;
	}

	@Override
	public NasmOperand visit(C3aConstant oper) {
		return new NasmConstant(oper.val);
	}

	@Override
	public NasmOperand visit(C3aBooleanConstant oper) {
		if (oper.val)
		{
			return new NasmConstant(1);
		}
		return new NasmConstant(0);
	}

	@Override
	public NasmOperand visit(C3aLabel oper) {
		return new NasmLabel(oper.toString());
	}

	public NasmOperand visit(C3aTemp temp){
		return new NasmRegister(temp.num);
    }

	@Override
	public NasmOperand visit(C3aVar oper) {
		if (currentFct.table.getVar(oper.item.identif)!=null)
		{
			return new NasmAddress(ebp,'-',new NasmConstant(4+oper.item.adresse));
		}
		else
		{
			return new NasmAddress(new NasmLabel(oper.item.identif));
		}
	}

	@Override
	public NasmOperand visit(C3aFunction oper) {
		return oper.accept(this);
	}

	/*--------------------------------------------------------------------------------------------------------------*/



}
