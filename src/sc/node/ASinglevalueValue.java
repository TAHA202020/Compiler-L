/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ASinglevalueValue extends PValue
{
    private PSinglevalue _singlevalue_;

    public ASinglevalueValue()
    {
        // Constructor
    }

    public ASinglevalueValue(
        @SuppressWarnings("hiding") PSinglevalue _singlevalue_)
    {
        // Constructor
        setSinglevalue(_singlevalue_);

    }

    @Override
    public Object clone()
    {
        return new ASinglevalueValue(
            cloneNode(this._singlevalue_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASinglevalueValue(this);
    }

    public PSinglevalue getSinglevalue()
    {
        return this._singlevalue_;
    }

    public void setSinglevalue(PSinglevalue node)
    {
        if(this._singlevalue_ != null)
        {
            this._singlevalue_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._singlevalue_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._singlevalue_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._singlevalue_ == child)
        {
            this._singlevalue_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._singlevalue_ == oldChild)
        {
            setSinglevalue((PSinglevalue) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
