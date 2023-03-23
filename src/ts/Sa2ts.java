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

	private Ts tableGlobale=new Ts();
	private Ts tableLocaleCourante;
	private Context context;

	public Ts getTableGlobale(){return this.tableGlobale;}

	public Sa2ts(SaNode root)
	{
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
		defaultIn(node);
		if (tableGlobale.getFct(node.getNom())==null)
		{
			tableLocaleCourante=new Ts();
			int nbrArgs;
			if (node.getParametres()!=null)
			{
				context=Context.PARAM;
				node.getParametres().accept(this);
				nbrArgs=node.getParametres().length();
			}
			else nbrArgs=0;
			if(node.getVariable()!=null)
			{
				context=Context.LOCAL;
				node.getVariable().accept(this);
			}
			if (node.getCorps()!=null)
			{
				context=Context.LOCAL;
				node.getCorps().accept(this);
			}
			String nom= node.getNom();
			Type type=node.getTypeRetour();
			node.tsItem=tableGlobale.addFct(nom,type,nbrArgs,tableLocaleCourante,node);
			context=Context.GLOBAL;
		}else {
			throw new ErrorException(Error.TS, "La fonction a été déja definie");
		}
		defaultOut(node);

		return null;
	}
	public Void visit(SaDecVar node)throws ErrorException
	{
		defaultIn(node);
		if (context==Context.PARAM)
		{
			if(tableLocaleCourante.getVar(node.getNom())==null)
			{
				node.setTsItem(tableLocaleCourante.addParam(node.getNom(),node.getType()));

			}else
			{
				throw new ErrorException(Error.TS,"param déja declarée");
			}
		}
		if (context==Context.LOCAL)
		{
			if(tableLocaleCourante.getVar(node.getNom())==null)
			{
				node.setTsItem(tableLocaleCourante.addVar(node.getNom(),node.getType()));
			}else
			{
				throw new ErrorException(Error.TS,"varibale déja declarée");
			}
		}
		if (context==Context.GLOBAL)
		{
			if(tableGlobale.getVar(node.getNom())==null) {
				node.setTsItem(tableGlobale.addVar(node.getNom(), node.getType()));
			}
			else
			{
				throw new ErrorException(Error.TS,"varibale déja declarée");
			}
		}
		defaultOut(node);

		return null;
	}
	public Void visit(SaDecTab node)throws ErrorException
	{
		defaultIn(node);
		if (context==Context.GLOBAL)
		{
			if(tableGlobale.getVar(node.getNom())==null)
			{
				node.setTsItem(tableGlobale.addTab(node.getNom(),node.getType(),node.getTaille()));
			}else
				throw new ErrorException(Error.TS,"variable deja declarée");

		}
		if (context==Context.LOCAL)
		{
			if(tableLocaleCourante.getVar(node.getNom())==null)
			{
				node.setTsItem(tableLocaleCourante.addTab(node.getNom(),node.getType(),node.getTaille()));
			}else
				throw new ErrorException(Error.TS,"variable deja declarée");
		}

		defaultOut(node);

		return null;
	}
	public Void visit(SaVarSimple node) throws ErrorException
	{
		System.out.println(node.getNom());
		defaultIn(node);
		if (tableGlobale.getVar(node.getNom())!=null)
		{
			node.tsItem=(TsItemVarSimple) tableGlobale.getVar(node.getNom());
		}
		else if (tableLocaleCourante.getVar(node.getNom())!=null)
		{
			node.tsItem=(TsItemVarSimple) tableLocaleCourante.getVar(node.getNom());
		}
		else
		{
			throw new ErrorException(Error.TS,"variable jamais declaré");
		}
		defaultOut(node);
		return null;
	}
	public Void visit(SaVarIndicee node)throws ErrorException
	{
		defaultIn(node);
		if (tableGlobale.getVar(node.getNom())!=null)
		{
			node.tsItem= tableGlobale.getVar(node.getNom());
		}
		else if (tableLocaleCourante.getVar(node.getNom())!=null)
		{
			node.tsItem= tableLocaleCourante.getVar(node.getNom());
		}
		else
		{
			throw new ErrorException(Error.TS,"variable jamais declaré");
		}
		defaultOut(node);
		return null;

	}
	public Void visit(SaAppel node) throws Exception
	{

		defaultIn(node);
		if (tableGlobale.getFct(node.getNom())==null)
		{
			throw new ErrorException(Error.TS,"fonction non declaré ");
		}
		if (node.getArguments()!=null)
		{
			node.getArguments().accept(this);
			if (node.getArguments().length()!=tableGlobale.getFct(node.getNom()).getNbArgs())
				throw  new ErrorException(Error.TS," nombre de parametre n'est pas correcte");
		}else
		{
			if (tableGlobale.getFct(node.getNom()).getNbArgs()>0)
				throw  new ErrorException(Error.TS," nombre de parametre n'est pas correcte");
		}

		node.tsItem=tableGlobale.getFct(node.getNom());
		defaultOut(node);
		return null;
	}


}