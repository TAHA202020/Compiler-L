/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ANotvalueValue extends PValue
{
    private TNot _not_;
    private PValue _value_;

    public ANotvalueValue()
    {
        // Constructor
    }

    public ANotvalueValue(
        @SuppressWarnings("hiding") TNot _not_,
        @SuppressWarnings("hiding") PValue _value_)
    {
        // Constructor
        setNot(_not_);

        setValue(_value_);

    }

    @Override
    public Object clone()
    {
        return new ANotvalueValue(
            cloneNode(this._not_),
            cloneNode(this._value_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotvalueValue(this);
    }

    public TNot getNot()
    {
        return this._not_;
    }

    public void setNot(TNot node)
    {
        if(this._not_ != null)
        {
            this._not_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._not_ = node;
    }

    public PValue getValue()
    {
        return this._value_;
    }

    public void setValue(PValue node)
    {
        if(this._value_ != null)
        {
            this._value_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._value_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._not_)
            + toString(this._value_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._not_ == child)
        {
            this._not_ = null;
            return;
        }

        if(this._value_ == child)
        {
            this._value_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._not_ == oldChild)
        {
            setNot((TNot) newChild);
            return;
        }

        if(this._value_ == oldChild)
        {
            setValue((PValue) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}