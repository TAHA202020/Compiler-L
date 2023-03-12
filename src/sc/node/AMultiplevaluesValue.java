/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AMultiplevaluesValue extends PValue
{
    private PSinglevalue _singlevalue_;
    private POperations _operations_;
    private PValue _value_;

    public AMultiplevaluesValue()
    {
        // Constructor
    }

    public AMultiplevaluesValue(
        @SuppressWarnings("hiding") PSinglevalue _singlevalue_,
        @SuppressWarnings("hiding") POperations _operations_,
        @SuppressWarnings("hiding") PValue _value_)
    {
        // Constructor
        setSinglevalue(_singlevalue_);

        setOperations(_operations_);

        setValue(_value_);

    }

    @Override
    public Object clone()
    {
        return new AMultiplevaluesValue(
            cloneNode(this._singlevalue_),
            cloneNode(this._operations_),
            cloneNode(this._value_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultiplevaluesValue(this);
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

    public POperations getOperations()
    {
        return this._operations_;
    }

    public void setOperations(POperations node)
    {
        if(this._operations_ != null)
        {
            this._operations_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._operations_ = node;
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
            + toString(this._singlevalue_)
            + toString(this._operations_)
            + toString(this._value_);
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

        if(this._operations_ == child)
        {
            this._operations_ = null;
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
        if(this._singlevalue_ == oldChild)
        {
            setSinglevalue((PSinglevalue) newChild);
            return;
        }

        if(this._operations_ == oldChild)
        {
            setOperations((POperations) newChild);
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