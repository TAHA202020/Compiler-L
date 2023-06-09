/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ACallfuncInstruction extends PInstruction
{
    private TIdentif _identif_;
    private TLp _lp_;
    private PNparam _nparam_;
    private TRp _rp_;
    private TPv _pv_;

    public ACallfuncInstruction()
    {
        // Constructor
    }

    public ACallfuncInstruction(
        @SuppressWarnings("hiding") TIdentif _identif_,
        @SuppressWarnings("hiding") TLp _lp_,
        @SuppressWarnings("hiding") PNparam _nparam_,
        @SuppressWarnings("hiding") TRp _rp_,
        @SuppressWarnings("hiding") TPv _pv_)
    {
        // Constructor
        setIdentif(_identif_);

        setLp(_lp_);

        setNparam(_nparam_);

        setRp(_rp_);

        setPv(_pv_);

    }

    @Override
    public Object clone()
    {
        return new ACallfuncInstruction(
            cloneNode(this._identif_),
            cloneNode(this._lp_),
            cloneNode(this._nparam_),
            cloneNode(this._rp_),
            cloneNode(this._pv_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACallfuncInstruction(this);
    }

    public TIdentif getIdentif()
    {
        return this._identif_;
    }

    public void setIdentif(TIdentif node)
    {
        if(this._identif_ != null)
        {
            this._identif_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identif_ = node;
    }

    public TLp getLp()
    {
        return this._lp_;
    }

    public void setLp(TLp node)
    {
        if(this._lp_ != null)
        {
            this._lp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lp_ = node;
    }

    public PNparam getNparam()
    {
        return this._nparam_;
    }

    public void setNparam(PNparam node)
    {
        if(this._nparam_ != null)
        {
            this._nparam_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._nparam_ = node;
    }

    public TRp getRp()
    {
        return this._rp_;
    }

    public void setRp(TRp node)
    {
        if(this._rp_ != null)
        {
            this._rp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rp_ = node;
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
            + toString(this._identif_)
            + toString(this._lp_)
            + toString(this._nparam_)
            + toString(this._rp_)
            + toString(this._pv_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._identif_ == child)
        {
            this._identif_ = null;
            return;
        }

        if(this._lp_ == child)
        {
            this._lp_ = null;
            return;
        }

        if(this._nparam_ == child)
        {
            this._nparam_ = null;
            return;
        }

        if(this._rp_ == child)
        {
            this._rp_ = null;
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
        if(this._identif_ == oldChild)
        {
            setIdentif((TIdentif) newChild);
            return;
        }

        if(this._lp_ == oldChild)
        {
            setLp((TLp) newChild);
            return;
        }

        if(this._nparam_ == oldChild)
        {
            setNparam((PNparam) newChild);
            return;
        }

        if(this._rp_ == oldChild)
        {
            setRp((TRp) newChild);
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
