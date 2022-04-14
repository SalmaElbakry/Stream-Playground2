package brickset;

import repository.Repository;

import javax.sound.midi.Soundbank;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }
//    Method 1

    /**
     * returns if there is a set with 0 number of pieces using anyMatch()
     * @return boolean
     */
    public boolean ZeroPieces(){
        return getAll().stream().anyMatch(LegoSet -> LegoSet.getPieces() == 0);}


//  Method 2

    /**
     * prints out distinct Tags sorted alphabetically using FlatMap
     */
    public void DistinctTagsAlphabeticallySorted()
    {
        getAll().stream()
                .filter(LegoSet -> LegoSet.getTags()!=null)
                .flatMap(LegoSet -> LegoSet.getTags().stream())
                .sorted()
                .distinct()
                .forEach(System.out::println);
    }
//    Method 3

    /**
     * returns total number of pieces of all lego sets using reduce
     * @return int
     */
    public int TotalNumberOfPieces(){
        return getAll().stream().
                map(LegoSet::getPieces).
                reduce(0, Integer::sum);
    }
//    Method 4

    /**
     *
     * @return map
     */
    public Map<String, LegoSet> LegoSetToNumber(){
        return getAll().
                stream().
                collect(toMap(LegoSet::getNumber, Function.identity()));
    }
//      Method 5

    /**
     *
     * @return Map
     */
    public Map<String, Optional<LegoSet>> NameToPieces(){
       return getAll().stream()
               .collect(groupingBy(LegoSet::getName, maxBy(Comparator.comparing(LegoSet::getPieces))));
    }



    public static void main (String[] args){
        var repository = new LegoSetRepository();
        System.out.println(repository.ZeroPieces());
        repository.DistinctTagsAlphabeticallySorted();
        System.out.println(repository.TotalNumberOfPieces());
        System.out.println(repository.LegoSetToNumber());
        System.out.println(repository.NameToPieces());


    }

}
