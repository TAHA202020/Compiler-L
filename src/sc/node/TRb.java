/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class TRb extends Token
{
    public TRb()
    {
        super.setText("[");
    }

    public TRb(int line, int pos)
    {
        super.setText("[");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TRb(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTRb(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TRb text.");
    }
}
