/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AParametervirgul extends PParametervirgul
{
    private PParameter _parameter_;
    private TVirgul _virgul_;

    public AParametervirgul()
    {
        // Constructor
    }

    public AParametervirgul(
        @SuppressWarnings("hiding") PParameter _parameter_,
        @SuppressWarnings("hiding") TVirgul _virgul_)
    {
        // Constructor
        setParameter(_parameter_);

        setVirgul(_virgul_);

    }

    @Override
    public Object clone()
    {
        return new AParametervirgul(
            cloneNode(this._parameter_),
            cloneNode(this._virgul_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAParametervirgul(this);
    }

    public PParameter getParameter()
    {
        return this._parameter_;
    }

    public void setParameter(PParameter node)
    {
        if(this._parameter_ != null)
        {
            this._parameter_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._parameter_ = node;
    }

    public TVirgul getVirgul()
    {
        return this._virgul_;
    }

    public void setVirgul(TVirgul node)
    {
        if(this._virgul_ != null)
        {
            this._virgul_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._virgul_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._parameter_)
            + toString(this._virgul_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._parameter_ == child)
        {
            this._parameter_ = null;
            return;
        }

        if(this._virgul_ == child)
        {
            this._virgul_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._parameter_ == oldChild)
        {
            setParameter((PParameter) newChild);
            return;
        }

        if(this._virgul_ == oldChild)
        {
            setVirgul((TVirgul) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}