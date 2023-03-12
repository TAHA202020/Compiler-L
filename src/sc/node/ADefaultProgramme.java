/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ADefaultProgramme extends PProgramme
{
    private PFunctionglobalvar _functionglobalvar_;
    private PProgramme _programme_;

    public ADefaultProgramme()
    {
        // Constructor
    }

    public ADefaultProgramme(
        @SuppressWarnings("hiding") PFunctionglobalvar _functionglobalvar_,
        @SuppressWarnings("hiding") PProgramme _programme_)
    {
        // Constructor
        setFunctionglobalvar(_functionglobalvar_);

        setProgramme(_programme_);

    }

    @Override
    public Object clone()
    {
        return new ADefaultProgramme(
            cloneNode(this._functionglobalvar_),
            cloneNode(this._programme_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADefaultProgramme(this);
    }

    public PFunctionglobalvar getFunctionglobalvar()
    {
        return this._functionglobalvar_;
    }

    public void setFunctionglobalvar(PFunctionglobalvar node)
    {
        if(this._functionglobalvar_ != null)
        {
            this._functionglobalvar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._functionglobalvar_ = node;
    }

    public PProgramme getProgramme()
    {
        return this._programme_;
    }

    public void setProgramme(PProgramme node)
    {
        if(this._programme_ != null)
        {
            this._programme_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._programme_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._functionglobalvar_)
            + toString(this._programme_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._functionglobalvar_ == child)
        {
            this._functionglobalvar_ = null;
            return;
        }

        if(this._programme_ == child)
        {
            this._programme_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._functionglobalvar_ == oldChild)
        {
            setFunctionglobalvar((PFunctionglobalvar) newChild);
            return;
        }

        if(this._programme_ == oldChild)
        {
            setProgramme((PProgramme) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
