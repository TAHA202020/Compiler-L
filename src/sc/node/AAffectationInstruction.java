/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AAffectationInstruction extends PInstruction
{
    private PAffectation _affectation_;
    private TPv _pv_;

    public AAffectationInstruction()
    {
        // Constructor
    }

    public AAffectationInstruction(
        @SuppressWarnings("hiding") PAffectation _affectation_,
        @SuppressWarnings("hiding") TPv _pv_)
    {
        // Constructor
        setAffectation(_affectation_);

        setPv(_pv_);

    }

    @Override
    public Object clone()
    {
        return new AAffectationInstruction(
            cloneNode(this._affectation_),
            cloneNode(this._pv_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAffectationInstruction(this);
    }

    public PAffectation getAffectation()
    {
        return this._affectation_;
    }

    public void setAffectation(PAffectation node)
    {
        if(this._affectation_ != null)
        {
            this._affectation_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._affectation_ = node;
    }

    public TPv getPv()
    {
        return this._pv_;
    }

    public void setPv(TPv node)
    {
        if(this._pv_ != null)
        {
            this._pv_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pv_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._affectation_)
            + toString(this._pv_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._affectation_ == child)
        {
            this._affectation_ = null;
            return;
        }

        if(this._pv_ == child)
        {
            this._pv_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._affectation_ == oldChild)
        {
            setAffectation((PAffectation) newChild);
            return;
        }

        if(this._pv_ == oldChild)
        {
            setPv((TPv) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}