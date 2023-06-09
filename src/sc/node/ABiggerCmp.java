/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ABiggerCmp extends PCmp
{
    private TBigger _bigger_;

    public ABiggerCmp()
    {
        // Constructor
    }

    public ABiggerCmp(
        @SuppressWarnings("hiding") TBigger _bigger_)
    {
        // Constructor
        setBigger(_bigger_);

    }

    @Override
    public Object clone()
    {
        return new ABiggerCmp(
            cloneNode(this._bigger_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseABiggerCmp(this);
    }

    public TBigger getBigger()
    {
        return this._bigger_;
    }

    public void setBigger(TBigger node)
    {
        if(this._bigger_ != null)
        {
            this._bigger_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._bigger_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._bigger_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._bigger_ == child)
        {
            this._bigger_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._bigger_ == oldChild)
        {
            setBigger((TBigger) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
