/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AMainProgramme extends PProgramme
{
    private PMain _main_;

    public AMainProgramme()
    {
        // Constructor
    }

    public AMainProgramme(
        @SuppressWarnings("hiding") PMain _main_)
    {
        // Constructor
        setMain(_main_);

    }

    @Override
    public Object clone()
    {
        return new AMainProgramme(
            cloneNode(this._main_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMainProgramme(this);
    }

    public PMain getMain()
    {
        return this._main_;
    }

    public void setMain(PMain node)
    {
        if(this._main_ != null)
        {
            this._main_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._main_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._main_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._main_ == child)
        {
            this._main_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._main_ == oldChild)
        {
            setMain((PMain) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
