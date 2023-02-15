/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AEqualanotherDeclare extends PDeclare
{
    private PType _type_;
    private TIdentif _first_;
    private TEqual _equal_;
    private TIdentif _second_;

    public AEqualanotherDeclare()
    {
        // Constructor
    }

    public AEqualanotherDeclare(
        @SuppressWarnings("hiding") PType _type_,
        @SuppressWarnings("hiding") TIdentif _first_,
        @SuppressWarnings("hiding") TEqual _equal_,
        @SuppressWarnings("hiding") TIdentif _second_)
    {
        // Constructor
        setType(_type_);

        setFirst(_first_);

        setEqual(_equal_);

        setSecond(_second_);

    }

    @Override
    public Object clone()
    {
        return new AEqualanotherDeclare(
            cloneNode(this._type_),
            cloneNode(this._first_),
            cloneNode(this._equal_),
            cloneNode(this._second_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEqualanotherDeclare(this);
    }

    public PType getType()
    {
        return this._type_;
    }

    public void setType(PType node)
    {
        if(this._type_ != null)
        {
            this._type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._type_ = node;
    }

    public TIdentif getFirst()
    {
        return this._first_;
    }

    public void setFirst(TIdentif node)
    {
        if(this._first_ != null)
        {
            this._first_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._first_ = node;
    }

    public TEqual getEqual()
    {
        return this._equal_;
    }

    public void setEqual(TEqual node)
    {
        if(this._equal_ != null)
        {
            this._equal_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._equal_ = node;
    }

    public TIdentif getSecond()
    {
        return this._second_;
    }

    public void setSecond(TIdentif node)
    {
        if(this._second_ != null)
        {
            this._second_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._second_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._type_)
            + toString(this._first_)
            + toString(this._equal_)
            + toString(this._second_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        if(this._first_ == child)
        {
            this._first_ = null;
            return;
        }

        if(this._equal_ == child)
        {
            this._equal_ = null;
            return;
        }

        if(this._second_ == child)
        {
            this._second_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

        if(this._first_ == oldChild)
        {
            setFirst((TIdentif) newChild);
            return;
        }

        if(this._equal_ == oldChild)
        {
            setEqual((TEqual) newChild);
            return;
        }

        if(this._second_ == oldChild)
        {
            setSecond((TIdentif) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}