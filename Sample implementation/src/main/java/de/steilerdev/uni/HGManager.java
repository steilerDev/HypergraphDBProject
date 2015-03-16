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
import org.hypergraphdb.*;
import org.hypergraphdb.util.HGUtils;

/**
 * This class extends the default HGEnvironment, including a new default get method for an hypergraph, as well as the possibility to create an example database.
 */
public class HGManager extends HGEnvironment
{
    /**
     * This string specifies the location of the database on the local disc.
     */
    public static String location = "/Users/FrankSteiler/Documents/Development/HypergraphDB/";

    /**
     * This function creates a default configuration for the HGEnvironment
     * @return An hypergraph from the pool provided by the HGEnvironment.
     */
    public static HyperGraph get()
    {
        HGConfiguration configuration  = new HGConfiguration();
        // Do configuration here

        return get(location, configuration);
    }

    /**
     * This function creates an example database.
     */
    public static void createExample()
    {
        System.out.println("### Dropping old database... ");
        HGUtils.dropHyperGraphInstance(location);

        System.out.println("### Creating database example... ");
        HyperGraph graph = HGManager.get();

        // Creating and adding all Books
        System.out.println("###### Creating and adding book entities...");
        HGHandle derGrosseGatsby =  graph.add(new Book("Der große Gatsby"));
        HGHandle zaertlichIstDieNacht = graph.add(new Book("Zärtlich ist die Nacht"));
        HGHandle schoeneNeueWelt = graph.add(new Book("Schöne neue Welt"));
        HGHandle homoFaber = graph.add(new Book("Homo Faber"));
        HGHandle montauk = graph.add(new Book("Montauk"));
        HGHandle derAlteMannUndDasMeer = graph.add(new Book("Der Alte Mann und das Meer"));
        HGHandle ueberDenFlussUndInDieWaelder = graph.add(new Book("Über den Fluss und in de Wälder"));
        HGHandle perAnhalterDurchDieGalaxis = graph.add(new Book("Per Anhalter durch die Galaxis"));
        HGHandle dasRestaurantAmEndeDesUniversums = graph.add(new Book("Das Restaurant am Ende des Universums"));
        HGHandle dasLebenDasUniversumUndDerGanzeRest = graph.add(new Book("Das Leben, das Universum und der ganze Rest"));
        HGHandle derSteppenwolf = graph.add(new Book("Der Steppenwolf"));
        HGHandle siddhartha = graph.add(new Book("Siddhartha"));

        // Creating and adding authors
        System.out.println("###### Creating and adding author entities...");
        HGHandle fScottFitzgerald = graph.add(new Author("Fitzgerald", "Francis Scott Key", "F. Scott Fitzgerald"));
        HGHandle aldousHuxley = graph.add(new Author("Huxley", "Aldous Leonard", "Aldous Huxley"));
        HGHandle maxFrisch = graph.add(new Author("Frisch", "Max Rudolf", "Max Frisch"));
        HGHandle ernestHemingway = graph.add(new Author("Hemingway", "Ernest Miller", "Ernest Hemingway"));
        HGHandle douglasAdams = graph.add(new Author("Adams", "Douglas Noël", "Douglas Adams"));
        HGHandle hermannHesse = graph.add(new Author("Hesse", "Hermann Karl", "Hermann Hesse"));

        // Creating and adding Publisher
        System.out.println("###### Creating and adding publisher...");
        HGHandle suhrkamp = graph.add(new Publisher("Suhrkamp"));
        HGHandle fischer = graph.add(new Publisher("Fischer Verlag"));
        HGHandle reclam = graph.add(new Publisher("Reclam"));
        HGHandle diogenes = graph.add(new Publisher("Diogenes"));
        HGHandle dtv = graph.add(new Publisher("dtv"));
        HGHandle rowohlt = graph.add(new Publisher("Rowohlt"));
        HGHandle heyne = graph.add(new Publisher("Heyne"));

        // Linking author to books
        System.out.println("###### Linking books and authors...");
        graph.add(new HasWritten(1925, fScottFitzgerald, derGrosseGatsby));
        graph.add(new HasWritten(1934, fScottFitzgerald, zaertlichIstDieNacht));
        graph.add(new HasWritten(1931, aldousHuxley, schoeneNeueWelt));
        graph.add(new HasWritten(1957, maxFrisch, homoFaber));
        graph.add(new HasWritten(1975, maxFrisch, montauk));
        graph.add(new HasWritten(1951, ernestHemingway, derAlteMannUndDasMeer));
        graph.add(new HasWritten(1950, ernestHemingway, ueberDenFlussUndInDieWaelder));
        graph.add(new HasWritten(1979, douglasAdams, perAnhalterDurchDieGalaxis));
        graph.add(new HasWritten(1980, douglasAdams, dasRestaurantAmEndeDesUniversums));
        graph.add(new HasWritten(1982, douglasAdams, dasLebenDasUniversumUndDerGanzeRest));
        graph.add(new HasWritten(1922, hermannHesse, siddhartha));
        graph.add(new HasWritten(1927, hermannHesse, derSteppenwolf));

        // Linking publisher to books
        System.out.println("###### Linking publisher and books");
        graph.add(new HasPublished(suhrkamp, homoFaber, montauk, derSteppenwolf, siddhartha));
//        graph.add(new HasPublished(suhrkamp, homoFaber));
//        graph.add(new HasPublished(suhrkamp, montauk));
//        graph.add(new HasPublished(suhrkamp, derSteppenwolf));
//        graph.add(new HasPublished(suhrkamp, siddhartha));

        graph.add(new HasPublished(diogenes, derGrosseGatsby));
        graph.add(new HasPublished(reclam, derGrosseGatsby));
        graph.add(new HasPublished(diogenes, zaertlichIstDieNacht));
        graph.add(new HasPublished(dtv, zaertlichIstDieNacht));
        graph.add(new HasPublished(fischer, schoeneNeueWelt));
        graph.add(new HasPublished(rowohlt, derAlteMannUndDasMeer));
        graph.add(new HasPublished(rowohlt, ueberDenFlussUndInDieWaelder));
        graph.add(new HasPublished(heyne, perAnhalterDurchDieGalaxis));
        graph.add(new HasPublished(heyne, dasRestaurantAmEndeDesUniversums));
        graph.add(new HasPublished(heyne, dasLebenDasUniversumUndDerGanzeRest));
        graph.add(new HasPublished(rowohlt, siddhartha));

        System.out.println("Finished building database");
        graph.close();
    }
}
