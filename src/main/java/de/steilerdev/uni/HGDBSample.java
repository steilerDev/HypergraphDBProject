package de.steilerdev.uni; /**
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

import org.hypergraphdb.*;

public class HGDBSample
{
    public static void main(String [] args)
    {
        HGManager.createExample();

        HyperGraph graph = HGManager.get();

        try
        {
            graph = new HyperGraph(databaseLocation);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        finally
        {
            if(graph != null)
                graph.close();
        }
    }
}