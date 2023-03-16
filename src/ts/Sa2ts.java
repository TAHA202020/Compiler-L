package ts;
import sa.*;
import util.Error;
import util.Type;

public class Sa2ts extends SaDepthFirstVisitor <Void> {
	enum Context {
		LOCAL,
		GLOBAL,
		PARAM
	}

	private Ts tableGlobale;
	private Ts tableLocaleCourante;
	private Context context;

	public Ts getTableGlobale(){return this.tableGlobale;}

	public Sa2ts(SaNode root)
	{
		tableGlobale = new Ts();
		tableLocaleCourante = null;
		context = Context.GLOBAL;
		try{
			root.accept(this);
			if(tableGlobale.getFct("main") == null)
				throw new ErrorException(Error.TS, "la fonction main n'est pas définie");
		}
		catch(ErrorException e){
			System.err.print("ERREUR TABLE DES SYMBOLES : ");
			System.err.println(e.getMessage());
			System.exit(e.getCode());
		}
		catch(Exception e){}
	}
	public Void visit(SaDecFonc node)throws Exception
	{
		if (tableGlobale.getFct(node.getNom())==null)
		{
			tableLocaleCourante=new Ts();
			if (node.getParametres()!=null)
			{
				context=Context.PARAM;
				node.getParametres().accept(this);
			}
			if(node.getVariable()!=null)
			{
				context=Context.LOCAL;
				node.getVariable().accept(this);
			}
			if (node.getCorps()!=null)
			{
				node.getCorps().accept(this);
			}
			String nom= node.getNom();
			Type type=node.getTypeRetour();
			tableGlobale.addFct(nom,type,node.getParametres().length(),tableLocaleCourante,node);
			context=Context.GLOBAL;
		}else {
			throw new ErrorException(Error.TS, "La fonction a été déja definie");
		}
		return null;
	}
	public Void visit(SaDecVar node)throws ErrorException
	{
		if (context==Context.PARAM)
		{
			if(tableLocaleCourante.getVar(node.getNom())==null)
			{
				tableLocaleCourante.addParam(node.getNom(),node.getType());
			}else
			{
				throw new ErrorException(Error.TS,"param déja declarée");
			}
		}
		if (context==Context.LOCAL)
		{
			if(tableLocaleCourante.getVar(node.getNom())==null)
			{
				tableLocaleCourante.addVar(node.getNom(),node.getType());
			}else
			{
				throw new ErrorException(Error.TS,"varibale déja declarée");
			}
		}
		if (context==Context.GLOBAL)
		{
			if(tableGlobale.getVar(node.getNom())==null) {
				tableGlobale.addVar(node.getNom(), node.getType());
			}
			else
			{
				throw new ErrorException(Error.TS,"varibale déja declarée");
			}
		}
		return null;
	}
	public Void visit(SaDecTab node)throws ErrorException
	{
		if (context==Context.GLOBAL)
		{
			if(tableGlobale.getVar(node.getNom())==null)
			{
				tableGlobale.addTab(node.getNom(),node.getType(),node.getTaille());
			}else
				throw new ErrorException(Error.TS,"variable deja declarée");

		}if (context==Context.LOCAL)
	{
		if(tableLocaleCourante.getVar(node.getNom())==null)
		{
			tableGlobale.addTab(node.getNom(),node.getType(),node.getTaille());
		}else
			throw new ErrorException(Error.TS,"variable deja declarée");
	}
		if (context==Context.PARAM)
		{
			throw new ErrorException(Error.TS,"table connot be in params");
		}
		return null;
	}
	public Void visit(SaVarSimple node)throws ErrorException
	{
		if (tableGlobale.getVar(node.getNom())==null && tableLocaleCourante.getVar(node.getNom())==null)
		{
			throw new ErrorException(Error.TS,"varibale jamais déclaré");
		}
		return null;
	}
	public Void visit(SaVarIndicee node)throws ErrorException
	{
		if (tableGlobale.getVar(node.getNom())==null && tableLocaleCourante.getVar(node.getNom())==null)
		{
			throw new ErrorException(Error.TS,"varibale jamais déclaré");

		}
		return null;
	}
	public Void visit(SaAppel node) throws ErrorException
	{
		if (tableGlobale.getFct(node.getNom())==null)
		{
			throw new ErrorException(Error.TS,"fonction non declaré ");
		}
		System.out.println(node.getArguments().length());
		System.out.println(tableGlobale.getFct(node.getNom()).getNbArgs());
		if (node.getArguments().length()!=tableGlobale.getFct(node.getNom()).getNbArgs())
			throw  new ErrorException(Error.TS," nombre de parametre n'est pas correcte");
		return null;
	}
}