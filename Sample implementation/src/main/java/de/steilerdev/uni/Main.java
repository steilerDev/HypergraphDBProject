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

import de.steilerdev.uni.model.Book;
import de.steilerdev.uni.model.Publisher;
import org.hypergraphdb.*;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("=========================================");
        System.out.println("=========================================");
        HGManager.createExample();
        System.out.println("=========================================");
        System.out.println("=========================================");
        System.out.println();


        HyperGraph graph = HGManager.get();
        HGQuery queryManager = new HGQuery(graph);
        System.out.println("=========================================");
        System.out.println("=========================================");
        //Finding book by its name
        List<Book> result = queryManager.findBookByTitleStandardQuery("Der große Gatsby");
        queryManager.findBookByTitleNamespaceQuery("Der große Gatsby");
        System.out.println("=========================================");
        System.out.println("=========================================");
        System.out.println();


        //Finding author by book
        if(result != null && !result.isEmpty())
        {
            System.out.println("=========================================");
            System.out.println("=========================================");
            queryManager.findAuthorByBook(result.get(0));
            System.out.println("=========================================");
            System.out.println("=========================================");
            System.out.println();
        }

        System.out.println("=========================================");
        System.out.println("=========================================");
        //Finding authors by publisher
        List<Publisher> publishers = queryManager.findPublisherByName("Suhrkamp");
        if(publishers != null && !publishers.isEmpty())
        {
            queryManager.findAuthorByPublisher(publishers.get(0));
        }
        System.out.println("=========================================");
        System.out.println("=========================================");
    }
}

