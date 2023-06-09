/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import sc.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAProgramme(AProgramme node);
    void caseAListedecfonc(AListedecfonc node);
    void caseAFonction(AFonction node);
    void caseAMain(AMain node);
    void caseAListedecvar(AListedecvar node);
    void caseAArguments(AArguments node);
    void caseABloc(ABloc node);
    void caseAAffectationInstruction(AAffectationInstruction node);
    void caseASiInstruction(ASiInstruction node);
    void caseATantqueInstruction(ATantqueInstruction node);
    void caseARetourInstruction(ARetourInstruction node);
    void caseAAppelInstruction(AAppelInstruction node);
    void caseAEcrireInstruction(AEcrireInstruction node);
    void caseALireInstruction(ALireInstruction node);
    void caseAFairetantqueInstruction(AFairetantqueInstruction node);
    void caseAFairetantque(AFairetantque node);
    void caseALire(ALire node);
    void caseAEcrire(AEcrire node);
    void caseAAffectation(AAffectation node);
    void caseASi(ASi node);
    void caseATantque(ATantque node);
    void caseARetour(ARetour node);
    void caseAAppel(AAppel node);
    void caseAAppelSansPv(AAppelSansPv node);
    void caseAExpression(AExpression node);
    void caseAMultiExpou(AMultiExpou node);
    void caseASingleExpou(ASingleExpou node);
    void caseAMultiExpet(AMultiExpet node);
    void caseASingleExpet(ASingleExpet node);
    void caseAMultiExpnon(AMultiExpnon node);
    void caseASingleExpnon(ASingleExpnon node);
    void caseAMultiExpComparaisonInf(AMultiExpComparaisonInf node);
    void caseASingleExpComparaisonInf(ASingleExpComparaisonInf node);
    void caseAMultiExpComparaisonEgale(AMultiExpComparaisonEgale node);
    void caseASingleExpComparaisonEgale(ASingleExpComparaisonEgale node);
    void caseAMultiExpAddition(AMultiExpAddition node);
    void caseASingleExpAddition(ASingleExpAddition node);
    void caseAMultiExpSub(AMultiExpSub node);
    void caseASingleExpSub(ASingleExpSub node);
    void caseAMultiExpMultiplication(AMultiExpMultiplication node);
    void caseASingleExpMultiplication(ASingleExpMultiplication node);
    void caseAMultiExpDiv(AMultiExpDiv node);
    void caseASingleExpDiv(ASingleExpDiv node);
    void caseAMultiExpCarre(AMultiExpCarre node);
    void caseASingleExpCarre(ASingleExpCarre node);
    void caseANombreExpressionPrimaire(ANombreExpressionPrimaire node);
    void caseAPExpressionExpressionPrimaire(APExpressionExpressionPrimaire node);
    void caseAValBoolExpressionPrimaire(AValBoolExpressionPrimaire node);
    void caseAAppelExpressionPrimaire(AAppelExpressionPrimaire node);
    void caseAVarExpressionPrimaire(AVarExpressionPrimaire node);
    void caseAVraiValeurbool(AVraiValeurbool node);
    void caseAFauxValeurbool(AFauxValeurbool node);
    void caseAEntierType(AEntierType node);
    void caseABoolType(ABoolType node);
    void caseAVvar(AVvar node);
    void caseAVarSimpleVar(AVarSimpleVar node);
    void caseATableauVar(ATableauVar node);
    void caseAVarSimpleVarname(AVarSimpleVarname node);
    void caseAVarTabVarname(AVarTabVarname node);
    void caseATypetab(ATypetab node);
    void caseATabval(ATabval node);
    void caseASinonbloc(ASinonbloc node);
    void caseAVexpression(AVexpression node);
    void caseAExpressionvexp(AExpressionvexp node);

    void caseTEspaces(TEspaces node);
    void caseTCommentaire(TCommentaire node);
    void caseTVirgule(TVirgule node);
    void caseTNombre(TNombre node);
    void caseTPv(TPv node);
    void caseTRp(TRp node);
    void caseTLp(TLp node);
    void caseTAo(TAo node);
    void caseTAf(TAf node);
    void caseTCo(TCo node);
    void caseTCf(TCf node);
    void caseTSFaire(TSFaire node);
    void caseTEntier(TEntier node);
    void caseTBool(TBool node);
    void caseTEgale(TEgale node);
    void caseTMainfc(TMainfc node);
    void caseTSSi(TSSi node);
    void caseTAlors(TAlors node);
    void caseTSinon(TSinon node);
    void caseTTantQue(TTantQue node);
    void caseTFaire(TFaire node);
    void caseTReturn(TReturn node);
    void caseTOr(TOr node);
    void caseTAnd(TAnd node);
    void caseTNot(TNot node);
    void caseTInf(TInf node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTCarre(TCarre node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTVrai(TVrai node);
    void caseTFaux(TFaux node);
    void caseTWrite(TWrite node);
    void caseTRead(TRead node);
    void caseTIdentif(TIdentif node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
