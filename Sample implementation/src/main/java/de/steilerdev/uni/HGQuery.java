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
package de.steilerdev.uni;

import de.steilerdev.uni.connections.HasPublished;
import de.steilerdev.uni.connections.HasWritten;
import de.steilerdev.uni.model.Author;
import de.steilerdev.uni.model.Book;
import de.steilerdev.uni.model.Publisher;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGLink;
import org.hypergraphdb.HGSearchResult;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.algorithms.*;
import org.hypergraphdb.query.*;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class HGQuery
{
    private HyperGraph graph;

    HGQuery(HyperGraph graph)
    {
        this.graph = graph;
    }

    public List<Book> findBookByTitleStandardQuery(String bookTitle)
    {
        System.out.println("Searching for books with title " + bookTitle);
        ArrayList<Book> resultSet = new ArrayList<Book>();

        HGQueryCondition condition = new And(
                new AtomTypeCondition(Book.class),
                new AtomPartCondition(
                        new String[]{"title"},
                        bookTitle,
                        ComparisonOperator.EQ
                )
        );

        HGSearchResult<HGHandle> rs = graph.find(condition);
        try
        {
            while(rs.hasNext())
            {
                HGHandle current = rs.next();
                Book book = graph.get(current);
                resultSet.add(book);
                System.out.println("Found: " + book.toString());
            }
        }
        finally
        {
            rs.close();
        }
        System.out.println("Returning list with " + resultSet.size() + (resultSet.size() == 1 ? " entry": " entries"));
        return resultSet;
    }

    public List<Book> findBookByTitleNamespaceQuery(String bookTitle)
    {
        System.out.println("Searching for books with title " + bookTitle + " using namespace queries");

        List<Book> resultSet = graph.getAll(
                hg.and(
                        hg.type(Book.class),
                        hg.eq("title", bookTitle)
                )
        );

        for (Book book : resultSet)
        {
            System.out.println("Found: " + book.toString());
        }

        System.out.println("Returning list with " + resultSet.size() + (resultSet.size() == 1 ? " entry": " entries"));
        return resultSet;
    }

    public List<Publisher> findPublisherByName(String publisherName)
    {
        System.out.println("Searching for publisher with name " + publisherName + " using namespace queries");

        List<Publisher> resultSet = graph.getAll(
                hg.and(
                        hg.type(Publisher.class),
                        hg.eq("name", publisherName)
                )
        );

        for (Publisher publisher : resultSet)
        {
            System.out.println("Found: " + publisher.toString());
        }

        System.out.println("Returning list with " + resultSet.size() + (resultSet.size() == 1 ? " entry": " entries"));
        return resultSet;
    }


    public List<Author> findAuthorByBook(Book book)
    {
        System.out.println("Searching for the author of the book " + book.toString());

        List<Author> resultSet = new ArrayList<Author>();

        HGHandle bookHandle = graph.getHandle(book);

        DefaultALGenerator algen = new DefaultALGenerator(
                graph,
                hg.type(HasWritten.class), //Which kind of connection "link predicate"
                hg.type(Author.class)); //Which kind of result class "sibling predicate"

        // First check connection type (link predicate), then check sibling predicate  (since the link can link to multiple siblings).


        HGTraversal traversal = new HGBreadthFirstTraversal(bookHandle, algen);

        while (traversal.hasNext())
        {
            Pair<HGHandle, HGHandle> next = traversal.next();
            Author nextAuthor = graph.get(next.getSecond());
            resultSet.add(nextAuthor);
            System.out.println("Found an author of the book: " + nextAuthor);
        }

        System.out.println("Returning list with " + resultSet.size() + (resultSet.size() == 1 ? " entry": " entries"));
        return resultSet;
    }

    public List<Author> findAuthorByPublisher(Publisher publisher)
    {
        System.out.println("Searching for the authors published by " + publisher.toString());

        List<Author> resultSet = new ArrayList<Author>();

        HGHandle publisherHandle = graph.getHandle(publisher);

        DefaultALGenerator algen = new DefaultALGenerator(
                graph,
                hg.or(hg.type(HasWritten.class), hg.type(HasPublished.class)),
                hg.or(hg.type(Author.class), hg.type(Book.class))
        );

        HGTraversal traversal = new HGBreadthFirstTraversal(publisherHandle, algen);

        while (traversal.hasNext())
        {
            Pair<HGHandle, HGHandle> next = traversal.next();

            if(graph.get(next.getSecond()).getClass() == Author.class)
            {
                Author nextAuthor = graph.get(next.getSecond());
                resultSet.add(nextAuthor);
                System.out.println("Found an author published by " + publisher.toString() + ": " + nextAuthor);
            }
        }

        System.out.println("Returning list with " + resultSet.size() + (resultSet.size() == 1 ? " entry": " entries"));
        return resultSet;
    }
}
