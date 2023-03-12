/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import java.util.*;
import sc.analysis.*;

@SuppressWarnings("nls")
public final class AParameters extends PParameters
{
    private final LinkedList<PParametervirgul> _parametervirgul_ = new LinkedList<PParametervirgul>();
    private PParameter _parameter_;

    public AParameters()
    {
        // Constructor
    }

    public AParameters(
        @SuppressWarnings("hiding") List<?> _parametervirgul_,
        @SuppressWarnings("hiding") PParameter _parameter_)
    {
        // Constructor
        setParametervirgul(_parametervirgul_);

        setParameter(_parameter_);

    }

    @Override
    public Object clone()
    {
        return new AParameters(
            cloneList(this._parametervirgul_),
            cloneNode(this._parameter_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAParameters(this);
    }

    public LinkedList<PParametervirgul> getParametervirgul()
    {
        return this._parametervirgul_;
    }

    public void setParametervirgul(List<?> list)
    {
        for(PParametervirgul e : this._parametervirgul_)
        {
            e.parent(null);
        }
        this._parametervirgul_.clear();

        for(Object obj_e : list)
        {
            PParametervirgul e = (PParametervirgul) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._parametervirgul_.add(e);
        }
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._parametervirgul_)
            + toString(this._parameter_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._parametervirgul_.remove(child))
        {
            return;
        }

        if(this._parameter_ == child)
        {
            this._parameter_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PParametervirgul> i = this._parametervirgul_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PParametervirgul) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._parameter_ == oldChild)
        {
            setParameter((PParameter) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}