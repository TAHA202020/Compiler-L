/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import java.util.*;
import sc.analysis.*;

@SuppressWarnings("nls")
public final class ACondition extends PCondition
{
    private TSi _si_;
    private PValue _value_;
    private TAlors _alors_;
    private TStartfunc _startfunc_;
    private final LinkedList<PInstruction> _instruction_ = new LinkedList<PInstruction>();
    private TEndfunc _endfunc_;
    private PElsecondition _elsecondition_;

    public ACondition()
    {
        // Constructor
    }

    public ACondition(
        @SuppressWarnings("hiding") TSi _si_,
        @SuppressWarnings("hiding") PValue _value_,
        @SuppressWarnings("hiding") TAlors _alors_,
        @SuppressWarnings("hiding") TStartfunc _startfunc_,
        @SuppressWarnings("hiding") List<?> _instruction_,
        @SuppressWarnings("hiding") TEndfunc _endfunc_,
        @SuppressWarnings("hiding") PElsecondition _elsecondition_)
    {
        // Constructor
        setSi(_si_);

        setValue(_value_);

        setAlors(_alors_);

        setStartfunc(_startfunc_);

        setInstruction(_instruction_);

        setEndfunc(_endfunc_);

        setElsecondition(_elsecondition_);

    }

    @Override
    public Object clone()
    {
        return new ACondition(
            cloneNode(this._si_),
            cloneNode(this._value_),
            cloneNode(this._alors_),
            cloneNode(this._startfunc_),
            cloneList(this._instruction_),
            cloneNode(this._endfunc_),
            cloneNode(this._elsecondition_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACondition(this);
    }

    public TSi getSi()
    {
        return this._si_;
    }

    public void setSi(TSi node)
    {
        if(this._si_ != null)
        {
            this._si_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._si_ = node;
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

    public TAlors getAlors()
    {
        return this._alors_;
    }

    public void setAlors(TAlors node)
    {
        if(this._alors_ != null)
        {
            this._alors_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._alors_ = node;
    }

    public TStartfunc getStartfunc()
    {
        return this._startfunc_;
    }

    public void setStartfunc(TStartfunc node)
    {
        if(this._startfunc_ != null)
        {
            this._startfunc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._startfunc_ = node;
    }

    public LinkedList<PInstruction> getInstruction()
    {
        return this._instruction_;
    }

    public void setInstruction(List<?> list)
    {
        for(PInstruction e : this._instruction_)
        {
            e.parent(null);
        }
        this._instruction_.clear();

        for(Object obj_e : list)
        {
            PInstruction e = (PInstruction) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._instruction_.add(e);
        }
    }

    public TEndfunc getEndfunc()
    {
        return this._endfunc_;
    }

    public void setEndfunc(TEndfunc node)
    {
        if(this._endfunc_ != null)
        {
            this._endfunc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._endfunc_ = node;
    }

    public PElsecondition getElsecondition()
    {
        return this._elsecondition_;
    }

    public void setElsecondition(PElsecondition node)
    {
        if(this._elsecondition_ != null)
        {
            this._elsecondition_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._elsecondition_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._si_)
            + toString(this._value_)
            + toString(this._alors_)
            + toString(this._startfunc_)
            + toString(this._instruction_)
            + toString(this._endfunc_)
            + toString(this._elsecondition_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._si_ == child)
        {
            this._si_ = null;
            return;
        }

        if(this._value_ == child)
        {
            this._value_ = null;
            return;
        }

        if(this._alors_ == child)
        {
            this._alors_ = null;
            return;
        }

        if(this._startfunc_ == child)
        {
            this._startfunc_ = null;
            return;
        }

        if(this._instruction_.remove(child))
        {
            return;
        }

        if(this._endfunc_ == child)
        {
            this._endfunc_ = null;
            return;
        }

        if(this._elsecondition_ == child)
        {
            this._elsecondition_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._si_ == oldChild)
        {
            setSi((TSi) newChild);
            return;
        }

        if(this._value_ == oldChild)
        {
            setValue((PValue) newChild);
            return;
        }

        if(this._alors_ == oldChild)
        {
            setAlors((TAlors) newChild);
            return;
        }

        if(this._startfunc_ == oldChild)
        {
            setStartfunc((TStartfunc) newChild);
            return;
        }

        for(ListIterator<PInstruction> i = this._instruction_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PInstruction) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._endfunc_ == oldChild)
        {
            setEndfunc((TEndfunc) newChild);
            return;
        }

        if(this._elsecondition_ == oldChild)
        {
            setElsecondition((PElsecondition) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
