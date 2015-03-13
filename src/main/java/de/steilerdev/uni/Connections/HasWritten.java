/**
 * Copyright (C) 2015 Frank Steiler <frank@steilerdev.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.steilerdev.uni.Connections;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

public class HasWritten extends HGPlainLink
{
    private int year;

    private String name;

    public HasWritten(HGHandle... outgoingSet)
    {
        super(outgoingSet);
    }

    public HasWritten(int year, HGHandle... outgoingSet)
    {
        super(outgoingSet);
        this.year = year;
        this.name = "HasWritten";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }
}
