# Movie Advisor (Arg based Spring Java app)
Spring based Java application that allows you to scrape through a csv with
movies data. You can display all movies or filter
by genre, different genres, year, between years, title or show a help message.

## Instructions
    -lg
List the different genres of movies in the collection.
No more parameters can be used.

    -ag genre1, genre2, genre ...
List movies that belong to at least one of the genres
indicated. The genre list cannot include spaces.

    -tg genre1, genre2, genre ...
List movies that belong to each one of the
genres indicated. The genre list cannot include spaces.

    -y year
List films that were released in the indicated year.
The year must be expressed in 4 digits.

    -b from, to
List those films that were released between the years FROM and TO.
The indicated years are included. They must be expressed with 4 figures.

    -t title
List those movies whose title contains the provided string.
The use of upper / lower case in the comparison will be ignored.

    -h
Show this help message.

### Examples of use
    java -jar movieadvisor.jar -y 2018 -ag Action, Comedy
Shows the 2018 action and / or comedy movies released.

    java -jar movieadvisor.jar -t heart
Shows movies whose title contains the word heart.

    java -jar movieadvisor.jar -td Action, Sci-Fi -b 1990,2010 -t star
Shows the movies whose title contains the word star, which belong to the
action genre and science fiction and were released between 1990 and 2010.

    java -jar movieadvisor.jar -lg
Shows a list with all genres.

## Licenses

Data used about movies used in this app is
real and is taken from the website [IMDb](https://www.imdb.com). Keep in mind
that can only be used for personal and non-commercial purposes.